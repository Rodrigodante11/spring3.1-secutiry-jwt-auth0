package com.rodrigo.feedbacksystem.enums;

public enum AreaTrabalho {

    ADMIN(1, "ADMIN"),
    TI(2, "TI"),
    TECNICO(3, "TECNICO"),
    RH(4, "RH");

    private Integer codigo;
    private String descricao;

    AreaTrabalho(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static AreaTrabalho toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for( AreaTrabalho x :AreaTrabalho.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Area de Trabalho Invalido!");
    }
}