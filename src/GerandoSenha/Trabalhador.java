
package GerandoSenha;


import java.io.File;
import net.lingala.zip4j.ZipFile;

public  class Trabalhador extends Thread {

    public Trabalhador(){
        
    }
    //Onde o arquivo está localizado.
       ZipFile zipFile = new ZipFile(new File("C:\\Users\\Downloads\\ArquivoSenha"));

    public ZipFile getZipFile() {
        return zipFile;
    }

    @Override
    public void run() {
        geraSenha7Caracteres();
        
    }

    public  void geraSenha7Caracteres() {

        //Criando a variavel e instanciando ela com 48 = 0, na Tabela Ascii
        int valoresCaractere[] = {48, 48, 48, 48, 48, 48, 48};

        while (valoresCaractere[0] <= 122 && valoresCaractere[1] <= 122
                && valoresCaractere[2] <= 122
                && valoresCaractere[3] <= 122
                && valoresCaractere[4] <= 122
                && valoresCaractere[5] <= 122
                && valoresCaractere[6] <= 122) {

            //Criando novo array pra tansformar para  7 simbolos
            char simbolos[] = new char[valoresCaractere.length];
            for (int i = 0; i < simbolos.length; i++) {
                //transformando os respectivos valores para simbolos
                simbolos[i] = (char) valoresCaractere[i];
            }

            //Gerando a String para teste da senha 
            String senha = new String(simbolos);

            System.out.println(senha);
            valoresCaractere[6]++;

            for (int i = valoresCaractere.length - 1; i >= 0; i--) {
                if (valoresCaractere[i] > 122) {
                    valoresCaractere[i - 1]++;
                    valoresCaractere[i] = 48;
                } else {
                    valoresCaractere[i] = ajustaValor(valoresCaractere[i]);
                }

            }

        }
    }

    //pulando os caracteres indesejados de acordo com a tabela ascii
    public  int ajustaValor(int ValorASCII) {
        switch (ValorASCII) {
            case 58 -> {
                return 65;
            }
            case 91 -> {
                return 97;
            }
        }
        return ValorASCII;
    }
}


