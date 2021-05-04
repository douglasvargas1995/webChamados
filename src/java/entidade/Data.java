package entidade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data {

    final public static int BR = 1;
    final public static int EN = 2;

    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        if (estaCorreta(dia, mes, ano)) {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        } else {
            throw new RuntimeException("Data incorreta. Objeto não instanciado.");
        }
    }

    public static boolean estaCorreta(int d, int m, int a) {
        boolean correto = false;
        if (a >= 1582) {
            if (m >= 1 && m <= 12) {
                int[] udm
                        = {
                            31, 28, 31, 30, 31,
                            30, 31, 31, 30, 31, 30, 31
                        };
                if (a % 400 == 0 || (a % 4 == 0 && a % 100 != 0)) {
                    udm[1] = 29;
                }
                if (d >= 1 && d <= udm[m - 1]) {
                    correto = true;
                }
            }
        }
        return correto;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public void setDia(int dia) {
        if (Data.estaCorreta(dia, this.mes, this.ano)) {
            this.dia = dia;
        }
    }

    public void setMes(int mes) {
        if (Data.estaCorreta(this.dia, mes, this.ano)) {
            this.mes = mes;
        }
    }

    public void setAno(int ano) {
        if (Data.estaCorreta(this.dia, this.mes, ano)) {
            this.ano = ano;
        }
    }

    public void avancarDias(int dias) // Huber
    {
        for (int i = 0; i < dias; i++) {
            this.avancarUmDia();
        }
    }

    public void avancarUmDia() // Huber
    {
        this.dia++;
        if (!Data.estaCorreta(this.dia, this.mes, this.ano)) {
            this.dia = 1;
            this.mes++;
            if (!Data.estaCorreta(this.dia, this.mes, this.ano)) {
                this.mes = 1;
                this.ano++;
            }
        }
    }

    public void retrocederDias(int dias) // Gustavo
    {
        for (int i = 0; i < dias; i++) {
            this.retrocederUmDia();
        }
    }

    /*
    public boolean isHorarioVerao() // Ferla
    {

    }
     */
    public int getTrimestre() //Tiago
    {
        int trimestre = 0;
        if (this.mes >= 1 && this.mes <= 3) {
            trimestre = 1;
        } else if (this.mes >= 4 && this.mes <= 6) {
            trimestre = 2;
        } else if (this.mes >= 7 && this.mes <= 9) {
            trimestre = 3;
        } else if (this.mes >= 10 && this.mes <= 12) {
            trimestre = 4;
        }
        return trimestre;
    }

    public int getAnosAteHoje() // idade // Lucas
    {
        Data dt = new Data(1, 1, 1980);
        dt.setComoHoje();
        int dias = this.getDiferenca(dt);
        return dias % 365;
    }

    public boolean isHoje() // Leo
    {
        Calendar c = Calendar.getInstance();

        int d = c.get(Calendar.DAY_OF_MONTH);
        int m = c.get(Calendar.MONTH) + 1;
        int a = c.get(Calendar.YEAR);

        return (this.dia == d && this.mes == m && this.ano == a);
    }

    public int getBimestre() // Tais
    {
        int bm = 0;
        if (this.mes == 1 || this.mes == 2) {
            bm = 1;

        } else if (this.mes == 3 || this.mes == 4) {
            bm = 2;

        } else if (this.mes == 5 || this.mes == 6) {
            bm = 3;

        } else if (this.mes == 7 || this.mes == 8) {
            bm = 4;

        } else if (this.mes == 9 || this.mes == 10) {
            bm = 5;

        } else if (this.mes == 11 || this.mes == 12) {
            bm = 6;
        }
        return bm;
    }

    public int getSemestre() // Tais
    {
        int sm = 2;
        if (this.mes >= 1 && this.mes <= 6) {
            sm = 1;
        }
        return sm;
    }

    public String getSigno() // Felipe
    {
        String signo = "";
        if ((this.dia >= 21 && this.mes == 3) || (this.dia <= 19 && this.mes == 4)) {
            signo = "Áries";
        } else if ((this.dia >= 20 && this.mes == 4) || (this.dia <= 20 && this.mes == 5)) {
            signo = "Touro";
        } else if ((this.dia >= 21 && this.mes == 5) || (this.dia <= 21 && this.mes == 6)) {
            signo = "Gêmeos";
        } else if ((this.dia >= 22 && this.mes == 6) || (this.dia <= 22 && this.mes == 7)) {
            signo = "Câncer";
        } else if ((this.dia >= 23 && this.mes == 7) || (this.dia <= 22 && this.mes == 8)) {
            signo = "Leão";
        } else if ((this.dia >= 23 && this.mes == 8) || (this.dia <= 22 && this.mes == 9)) {
            signo = "Virgem";
        } else if ((this.dia > 23 && this.mes == 9) || (this.dia <= 22 && this.mes == 10)) {
            signo = "Libra";
        } else if ((this.dia >= 23 && this.mes == 10) || (this.dia <= 21 && this.mes == 11)) {
            signo = "Escorpião";
        } else if ((this.dia >= 22 && this.mes == 11) || (this.dia <= 21 && this.mes == 12)) {
            signo = "Sagitário";
        } else if ((this.dia >= 22 && this.mes == 12) || (this.dia <= 19 && this.mes == 1)) {
            signo = "Capricórnio";
        } else if ((this.dia >= 20 && this.mes == 1) || (this.dia <= 18 && this.mes == 2)) {
            signo = "Aquário";
        } else if ((this.dia >= 19 && this.mes == 2) || (this.dia <= 20 && this.mes == 3)) {
            signo = "Peixes";
        }
        return signo;
    }

    public boolean isFeriado(int dia, int mes) // Luis
    {
        boolean ok = false;
        Data dtUniversal = new Data(01, 1, ano);
        Data dtTiradentes = new Data(21, 4, ano);
        Data dtTrabalhador = new Data(01, 5, ano);
        Data dtPatria = new Data(07, 9, ano);
        Data dtNossaSenhora = new Data(12, 10, ano);
        Data dtFinados = new Data(02, 11, ano);
        Data dtProclaRepublica = new Data(15, 11, ano);
        Data dtNatal = new Data(25, 12, ano);

        if (this.equals(dtUniversal)
                || this.equals(dtTiradentes)
                || this.equals(dtTrabalhador)
                || this.equals(dtPatria)
                || this.equals(dtNossaSenhora)
                || this.equals(dtFinados)
                || this.equals(dtProclaRepublica)
                || this.equals(dtNatal)) {
            ok = true;

        }
        return ok;
    }

    public String getEstacaoDoAno() // Micaela
    {
        String estacao;
        if ((this.dia >= 21 && this.mes >= 3) && (this.dia <= 20 && this.mes <= 06)) {
            estacao = "Outono";
        } else if ((this.dia >= 21 && this.mes >= 6) && (this.dia <= 20 && this.mes <= 10)) {
            estacao = "Inverno";
        } else if ((this.dia >= 21 && this.mes >= 10) && (this.dia <= 20 && this.mes <= 12)) {
            estacao = "Primavera";
        } else {
            estacao = "Verão";
        }
        return estacao;
    }

    public boolean isAnoBissexto() // Bianca
    {
        boolean bissexto = false;
        if (ano % 4 == 0) {
            bissexto = true;
            if (this.ano % 100 == 0) {
                bissexto = false;
                if (this.ano % 400 == 0) {
                    bissexto = true;
                }
            }
        }
        return bissexto;
    }

    public String getDataFormatada(int formato) // Bruno
    {
        String data = "";
        if (formato == 1) // BR
        {
            data = (this.dia < 10 ? "0" + dia : "" + dia) + "/" + (this.mes < 10 ? "0" + mes : "" + mes) + "/" + this.ano;
        } else if (formato == 2) {
            data = this.ano + "/" + (this.mes < 10 ? "0" + mes : "" + mes) + "/" + (this.dia < 10 ? "0" + dia : "" + dia);
        }
        return data;
    }

    public boolean equals(Data dt) // Douglas   
    {
        if (this.dia == dt.dia && this.mes == dt.mes && this.ano == dt.ano) {
            return true;
        }
        return false;
    }

    public String getExtensoMes() // Patrick
    {

        String vetMes[]
                = {
                    "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                    "Julho", "Agosto", "Setembro", "Outubro", "Novembro",
                    "Dezembro"
                };

        return vetMes[this.mes - 1];
    }

    public String getDiaDaSemana() // Nichollas
    {
        int dias = this.getDiaDoSeculo();
        int resto = dias % 7;

        String[] ds
                = {
                    "Domingo", "Segunda-feira", "Terça-feira",
                    "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"
                };
        return ds[resto];
    }

    public void retrocederUmDia() // Filipe
    {
        int[] udm
                = {
                    31, 28, 31, 30, 31,
                    30, 31, 31, 30, 31, 30, 31
                };
        if (this.ano % 400 == 0 || (this.ano % 4 == 0 && this.ano % 100 != 0)) {
            udm[1] = 29;
        }

        this.dia--;
        if (dia == 0) {
            this.mes--;
            if (mes == 0) {
                this.ano--;
                this.mes = 12;
            }
            this.dia = udm[this.mes - 1];
        }
    }

    public int getDiaDoAno() // Willy
    {
        Data dt = new Data(31, 12, this.ano - 1);
        return this.getDiferenca(dt);
    }

    public int getSemanaDoAno() // Luis
    {
        int semanaAno;
        semanaAno = (int) (getDiaDoAno() / 7);
        if (getDiaDoAno() % 7 > 0) {
            semanaAno++;
        }
        return semanaAno;
    }

    public int getSeculo() // Micaela
    {
        int seculo;
        seculo = (int) (this.ano / 100) + 1;
        if (this.ano % 100 == 0) {
            seculo--;
        }
        return seculo;

    }

    public void setComoHoje() // Bianca
    {
        Calendar c = Calendar.getInstance();

        int d = c.get(Calendar.DAY_OF_MONTH);
        int m = c.get(Calendar.MONTH) + 1;
        int a = c.get(Calendar.YEAR);

        this.dia = d;
        this.mes = m;
        this.ano = a;
    }

    public int compareTo(Data dt) // Gustavo e Douglas
    {
        /*
        String d1 = this.getDataFormatada( Data.EN );
        String d2 = dt.getDataFormatada( Data.EN );
        
        return d1.compareTo(d2);
         */

        int d1 = this.getDiaDoSeculo();
        int d2 = dt.getDiaDoSeculo();

        int r = 0;
        if (d1 > d2) {
            r = 1;
        } else if (d1 < d2) {
            r = -1;
        }
        return r;
    }

    public int getDiferenca(Data dt) // Alecrim
    { //130 dias.
        int diferenca;
        int diaSecDataAtual = this.getDiaDoSeculo();
        int diaSecDataEsperada = dt.getDiaDoSeculo();
        diferenca = Math.abs(diaSecDataAtual - diaSecDataEsperada);
        return diferenca;
    }

    public Data clone() // Mouriac
    {
        return new Data(this.dia, this.mes, this.ano);
    }

    public String getDataExtenso() // Mouriac
    {
        // Segunda-feira, 14 de abril de 2019.
        return this.getDiaDaSemana() + ", " + this.getDia() + " de " + this.getExtensoMes() + " de " + this.getAno();
    }

    /**
     * Retorna o número de dias transcorridos desde 01/01/1901
     */
    private int getDiaDoSeculo() // Mouriac
    {
        //www.inf.ufrgs.br/~cabral/Dia_do_Seculo.html
        int diaDoSeculo = (ano - 1901) * 365 + (ano - 1901) / 4 + dia + (mes - 1)
                * 31 - ((mes * 4 + 23) / 10)
                * ((mes + 12) / 15) + ((4 - ano % 4) / 4)
                * ((mes + 12) / 15);
        return diaDoSeculo;
    }

    //metodo que verifica se a data digitada é maior que a de hoje
    public static boolean verificarData(String dt2) throws ParseException {

        String dataAtual = Formatacao.getDataAtual();
        String formato = "dd/MM/yyyy";
        Date data1 = new SimpleDateFormat(formato).parse(dataAtual);
        Date data2 = new SimpleDateFormat(formato).parse(dt2);
        boolean retorno = false;
        
        if (data2.after(data1)) {//data 2 vem depois da data 1(data atual)?
            retorno = true;
        } 

        return retorno;
    }
    
     //metodo que verifica se a data1 é menor que a data2
    public static boolean verificarDatas(String dt1, String dt2) throws ParseException {

        String formato = "dd/MM/yyyy";
        Date data1 = new SimpleDateFormat(formato).parse(dt1);
        Date data2 = new SimpleDateFormat(formato).parse(dt2);
        boolean retorno = false;
        
        if (data2.after(data1)) {//data 2 vem depois da data 1(data1)?
            retorno = true;
        } 

        return retorno;
    }
    
    public static String formatarData (Date dt) throws Exception{
       
       java.util.Date pega = dt;
       SimpleDateFormat formato = new SimpleDateFormat("d/M/y");
       String data = formato.format(pega);
      // java.sql.Date dataFormatada = new java.sql.Date(formato.parse(data).getTime());
       
       return data;
       
    }
    
}
