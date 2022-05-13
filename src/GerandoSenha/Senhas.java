/*
Wesley Assis
*/
package GerandoSenha;

import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class Senhas {

    public static void main(String[] args) {

        
        //Número de Threads de acordo com cada Computador ou Notebook.
        Trabalhador trabs[] = {new Trabalhador(),
            new Trabalhador(),
            new Trabalhador(),
            new Trabalhador(),
            new Trabalhador(),
            new Trabalhador(),
            new Trabalhador(),
            new Trabalhador(),
            new Trabalhador(),
            new Trabalhador(),
            new Trabalhador()};

        //Iniciando as Threads
        for (GerandoSenha.Trabalhador trab : trabs) {
            trab.start();
        }
        //Esperar o término de todas as Threads
        for (GerandoSenha.Trabalhador trab : trabs) {
            try {
                trab.join();
            } catch (InterruptedException ex) {
                System.out.println("Erro");
            }
        }

        //Criando um for de acordo com a quantidade de Threads,ou seja, 
        //será 12 combinações simultâneas,(porém cada um testando de um jeito 
        //diferente,para encontrar a Senha do arquivo e descriptografar.
        for (Trabalhador trab : trabs) {
            try {
                if (trab.getZipFile().isEncrypted()) {
                    trab.getZipFile().setPassword("".toCharArray());
                }
                List fileHeaderList = trab.getZipFile().getFileHeaders();
                for (int j = 0; j < fileHeaderList.size(); j++) {
                    FileHeader fileHeader = (FileHeader) fileHeaderList.get(j);
                    trab.getZipFile().extractFile(fileHeader, "Downloads");
                    System.out.println("Sucesso essa é a senha");
                }
            } catch (ZipException e) {
                System.out.println("Senha incorreta");
            }
        }

    }
}
