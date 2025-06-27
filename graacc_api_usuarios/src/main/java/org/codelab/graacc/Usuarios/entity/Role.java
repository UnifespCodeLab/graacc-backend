package org.codelab.graacc.Usuarios.entity;

public enum Role {
    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private Integer codigo;
    private String descricao;

    Role(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
