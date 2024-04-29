# LATTES REPORT
# Projeto de Análise de Dados Lattes

Este é um projeto desenvolvido em Java para análise de dados provenientes da Plataforma Lattes. Ele recebe como entrada arquivos .xml que representam perfis de currículos Lattes e permite a geração de relatórios com base nessas informações.

## Como funciona

1. **Entrada de Dados:**
   - Os arquivos .xml devem ser colocados no diretório `files` para serem processados pelo sistema. Esses arquivos são gerados a partir dos perfis de currículos na Plataforma Lattes.

2. **Processamento e Manipulação:**
   - O sistema realiza a leitura e manipulação dos arquivos .xml para extrair informações relevantes dos currículos.

3. **Geração de Relatórios:**
   - Com base nos dados dos currículos, é possível aplicar filtros específicos para extrair informações desejadas. Os relatórios gerados podem ser exportados e salvos no diretório `relatorios_salvos`.

## Requisitos

- Java 8 ou superior
- Bibliotecas adicionais (se houver) estão listadas no arquivo `pom.xml` para gerenciamento de dependências com Maven.

## Como usar

1. Clone este repositório:
   ```
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   ```

3. Coloque os arquivos .xml na pasta `files`.

4. Compile o projeto:
    ```
    mvn clean install
    ```

4. Execute o projeto:
    ```
    java -jar nome-do-arquivo.jar
    ```
    
5. Siga as instruções para aplicar filtros e gerar relatórios.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests com melhorias, correções de bugs ou novos recursos.

## Autores

- [Daniel dos Santos](https://github.com/dsantosr)
- [Welderson Bruce](https://github.com/brvcelose)

## Licença

Este projeto está licenciado sob a [MIT](https://opensource.org/license/mit).
