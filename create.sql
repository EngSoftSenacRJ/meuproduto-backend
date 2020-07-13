
    create table revision_info (
       id bigint not null auto_increment,
        timestamp datetime not null,
        primary key (id)
    ) engine=MyISAM

    create table tb_categoria_produto (
       id bigint not null auto_increment,
        descricao varchar(255),
        nome varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table tb_categoria_produto_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        descricao varchar(255),
        nome varchar(255),
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_horario_funcionamento (
       id bigint not null auto_increment,
        aberto bit,
        dia_semana varchar(255),
        horario_funcionamento_ate time,
        horario_funcionamento_de time,
        id_loja bigint,
        primary key (id)
    ) engine=MyISAM

    create table tb_loja (
       id bigint not null auto_increment,
        bairro_endereco_comercial varchar(50) not null,
        cep_endereco_comercial varchar(255) not null,
        cidade_endereco_comercial varchar(50) not null,
        cnpj varchar(255) not null,
        data_criacao datetime,
        email_usuario_criador_loja varchar(255) not null,
        estado_endereco_comercial varchar(50) not null,
        latitude double precision,
        longitude double precision,
        nome varchar(255) not null,
        numero_endereco_comercial varchar(255) not null,
        razao_social varchar(100) not null,
        rua_endereco_comercial varchar(50) not null,
        telefone_contato varchar(255) not null,
        primary key (id)
    ) engine=MyISAM

    create table tb_loja_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        bairro_endereco_comercial varchar(255),
        cep_endereco_comercial varchar(255),
        cidade_endereco_comercial varchar(255),
        cnpj varchar(255),
        data_criacao datetime,
        email_usuario_criador_loja varchar(255),
        estado_endereco_comercial varchar(255),
        latitude double precision,
        longitude double precision,
        nome varchar(255),
        numero_endereco_comercial varchar(255),
        razao_social varchar(255),
        rua_endereco_comercial varchar(255),
        telefone_contato varchar(255),
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_loja_produto (
       id bigint not null auto_increment,
        preco double precision,
        id_loja bigint,
        id_produto bigint,
        primary key (id)
    ) engine=MyISAM

    create table tb_loja_produto_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        preco double precision,
        id_loja bigint,
        id_produto bigint,
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_marca_produto (
       id bigint not null auto_increment,
        descricao varchar(200) not null,
        habilitado bit,
        nome varchar(50) not null,
        primary key (id)
    ) engine=MyISAM

    create table tb_marca_produto_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        descricao varchar(255),
        habilitado bit,
        nome varchar(255),
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_parameter (
       id bigint not null auto_increment,
        descricao varchar(255),
        nome varchar(255),
        valor varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table tb_produto (
       id bigint not null auto_increment,
        descricao varchar(100),
        imagem varchar(255),
        meses_garantia integer not null,
        nome varchar(50) not null,
        id_categoria bigint not null,
        id_marca bigint not null,
        primary key (id)
    ) engine=MyISAM

    create table tb_produto_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        descricao varchar(255),
        imagem varchar(255),
        meses_garantia integer,
        nome varchar(255),
        id_categoria bigint,
        id_marca bigint,
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_usuario (
       type varchar(31) not null,
        id bigint not null auto_increment,
        bairro_endereco_pessoal varchar(50),
        cep_endereco_pessoal varchar(255),
        cidade_endereco_pessoal varchar(50),
        cpf bigint,
        data_aniversario date,
        data_criacao date,
        email_confirmado bit,
        habilitado bit,
        estado_endereco_pessoal varchar(50),
        nome varchar(255),
        numero_endereco_pessoal varchar(255),
        senha varchar(255),
        rua_endereco_pessoal varchar(50),
        telefone_contato bigint,
        token_validacao_email varchar(255),
        email varchar(255),
        username_administrador varchar(255),
        loja_id bigint,
        primary key (id)
    ) engine=MyISAM

    create table tb_usuario_loja (
       id_usuario bigint not null,
        id_loja bigint not null,
        primary key (id_usuario, id_loja)
    ) engine=MyISAM

    alter table tb_categoria_produto_aud 
       add constraint FKny53qy0ak07srbch73uru0n6q 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_horario_funcionamento 
       add constraint FKpm229vmw3h4x69owu4o285ha6 
       foreign key (id_loja) 
       references tb_loja (id)

    alter table tb_loja_aud 
       add constraint FK7ytqbwr8q7p0kpnxq7cu1uj0p 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_loja_produto 
       add constraint FKs75yr54cgdytjvyqsqjxk5rdr 
       foreign key (id_loja) 
       references tb_loja (id)

    alter table tb_loja_produto 
       add constraint FK49tk3vrj2jge03wrl3hifcj5l 
       foreign key (id_produto) 
       references tb_produto (id)

    alter table tb_loja_produto_aud 
       add constraint FK3dhc1t8d989a0fnb2noe8450g 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_marca_produto_aud 
       add constraint FKmxl2p77pyli2lcsmel6t24nba 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_produto 
       add constraint FKlw0pxh6k0dm2njwe1h11vrbca 
       foreign key (id_categoria) 
       references tb_categoria_produto (id)

    alter table tb_produto 
       add constraint FK6crmi654fdex2wms41t813mcx 
       foreign key (id_marca) 
       references tb_marca_produto (id)

    alter table tb_produto_aud 
       add constraint FKse77fem5vcgxephyxn04xrp9u 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_usuario 
       add constraint FKlw7j1148be2kt3irt7br60cuf 
       foreign key (loja_id) 
       references tb_loja (id)

    alter table tb_usuario_loja 
       add constraint FKfp0rnq7kl1std374f690el9jf 
       foreign key (id_loja) 
       references tb_loja (id)

    alter table tb_usuario_loja 
       add constraint FKi3rl88wjtdnu6vv23sophb9er 
       foreign key (id_usuario) 
       references tb_usuario (id)

    create table revision_info (
       id bigint not null auto_increment,
        timestamp datetime not null,
        primary key (id)
    ) engine=MyISAM

    create table tb_categoria_produto (
       id bigint not null auto_increment,
        descricao varchar(255),
        nome varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table tb_categoria_produto_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        descricao varchar(255),
        nome varchar(255),
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_horario_funcionamento (
       id bigint not null auto_increment,
        aberto bit,
        dia_semana varchar(255),
        horario_funcionamento_ate time,
        horario_funcionamento_de time,
        id_loja bigint,
        primary key (id)
    ) engine=MyISAM

    create table tb_loja (
       id bigint not null auto_increment,
        bairro_endereco_comercial varchar(50) not null,
        cep_endereco_comercial varchar(255) not null,
        cidade_endereco_comercial varchar(50) not null,
        cnpj varchar(255) not null,
        data_criacao datetime,
        email_usuario_criador_loja varchar(255) not null,
        estado_endereco_comercial varchar(50) not null,
        latitude double precision,
        longitude double precision,
        nome varchar(255) not null,
        numero_endereco_comercial varchar(255) not null,
        razao_social varchar(100) not null,
        rua_endereco_comercial varchar(50) not null,
        telefone_contato varchar(255) not null,
        primary key (id)
    ) engine=MyISAM

    create table tb_loja_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        bairro_endereco_comercial varchar(255),
        cep_endereco_comercial varchar(255),
        cidade_endereco_comercial varchar(255),
        cnpj varchar(255),
        data_criacao datetime,
        email_usuario_criador_loja varchar(255),
        estado_endereco_comercial varchar(255),
        latitude double precision,
        longitude double precision,
        nome varchar(255),
        numero_endereco_comercial varchar(255),
        razao_social varchar(255),
        rua_endereco_comercial varchar(255),
        telefone_contato varchar(255),
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_loja_produto (
       id bigint not null auto_increment,
        preco double precision,
        id_loja bigint,
        id_produto bigint,
        primary key (id)
    ) engine=MyISAM

    create table tb_loja_produto_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        preco double precision,
        id_loja bigint,
        id_produto bigint,
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_marca_produto (
       id bigint not null auto_increment,
        descricao varchar(200) not null,
        habilitado bit,
        nome varchar(50) not null,
        primary key (id)
    ) engine=MyISAM

    create table tb_marca_produto_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        descricao varchar(255),
        habilitado bit,
        nome varchar(255),
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_parameter (
       id bigint not null auto_increment,
        descricao varchar(255),
        nome varchar(255),
        valor varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table tb_produto (
       id bigint not null auto_increment,
        descricao varchar(100),
        imagem varchar(255),
        meses_garantia integer not null,
        nome varchar(50) not null,
        id_categoria bigint not null,
        id_marca bigint not null,
        primary key (id)
    ) engine=MyISAM

    create table tb_produto_aud (
       id bigint not null,
        rev bigint not null,
        revtype tinyint,
        descricao varchar(255),
        imagem varchar(255),
        meses_garantia integer,
        nome varchar(255),
        id_categoria bigint,
        id_marca bigint,
        primary key (id, rev)
    ) engine=MyISAM

    create table tb_usuario (
       type varchar(31) not null,
        id bigint not null auto_increment,
        bairro_endereco_pessoal varchar(50),
        cep_endereco_pessoal varchar(255),
        cidade_endereco_pessoal varchar(50),
        cpf bigint,
        data_aniversario date,
        data_criacao date,
        email_confirmado bit,
        habilitado bit,
        estado_endereco_pessoal varchar(50),
        nome varchar(255),
        numero_endereco_pessoal varchar(255),
        senha varchar(255),
        rua_endereco_pessoal varchar(50),
        telefone_contato bigint,
        token_validacao_email varchar(255),
        email varchar(255),
        username_administrador varchar(255),
        loja_id bigint,
        primary key (id)
    ) engine=MyISAM

    create table tb_usuario_loja (
       id_usuario bigint not null,
        id_loja bigint not null,
        primary key (id_usuario, id_loja)
    ) engine=MyISAM

    alter table tb_categoria_produto_aud 
       add constraint FKny53qy0ak07srbch73uru0n6q 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_horario_funcionamento 
       add constraint FKpm229vmw3h4x69owu4o285ha6 
       foreign key (id_loja) 
       references tb_loja (id)

    alter table tb_loja_aud 
       add constraint FK7ytqbwr8q7p0kpnxq7cu1uj0p 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_loja_produto 
       add constraint FKs75yr54cgdytjvyqsqjxk5rdr 
       foreign key (id_loja) 
       references tb_loja (id)

    alter table tb_loja_produto 
       add constraint FK49tk3vrj2jge03wrl3hifcj5l 
       foreign key (id_produto) 
       references tb_produto (id)

    alter table tb_loja_produto_aud 
       add constraint FK3dhc1t8d989a0fnb2noe8450g 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_marca_produto_aud 
       add constraint FKmxl2p77pyli2lcsmel6t24nba 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_produto 
       add constraint FKlw0pxh6k0dm2njwe1h11vrbca 
       foreign key (id_categoria) 
       references tb_categoria_produto (id)

    alter table tb_produto 
       add constraint FK6crmi654fdex2wms41t813mcx 
       foreign key (id_marca) 
       references tb_marca_produto (id)

    alter table tb_produto_aud 
       add constraint FKse77fem5vcgxephyxn04xrp9u 
       foreign key (rev) 
       references revision_info (id)

    alter table tb_usuario 
       add constraint FKlw7j1148be2kt3irt7br60cuf 
       foreign key (loja_id) 
       references tb_loja (id)

    alter table tb_usuario_loja 
       add constraint FKfp0rnq7kl1std374f690el9jf 
       foreign key (id_loja) 
       references tb_loja (id)

    alter table tb_usuario_loja 
       add constraint FKi3rl88wjtdnu6vv23sophb9er 
       foreign key (id_usuario) 
       references tb_usuario (id)
