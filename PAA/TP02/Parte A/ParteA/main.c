#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "caca_palavras.h"

int main() {
    Tabuleiro c;
    int opcao = -1, modo, carregadoSucesso = 0;
    char nomeArq[50], palavra[50];

    while (opcao != 0) {
        printf(" * * * * * * * * * * * M E N U * * * * * * * * * * *");
        printf("\n *                  CACA-PALAVRAS                  *");
        printf("\n * Opcoes disponiveis:                             *");
        printf("\n * 1- Carregar Arquivo                             *");
        printf("\n * 2- Pesquisar Palavra                            *");
        printf("\n * 0- SAIR                                         *");
        printf("\n * * * * * * * * * * * * * * * * * * * * * * * * * *");


        do {
            printf("\n  Escolha uma opcao:");
            fflush(stdout);
            scanf("%d", &opcao);
            fflush(stdout);
        } while (opcao < 0 || opcao > 2);

        switch(opcao){
            case 1:
                printf("\n Nome do arquivo a ser carregado: ");
                fflush(stdout);
                scanf("%s", &nomeArq);
                fflush(stdout);
                if (carregaArquivo(nomeArq, &c)) {
                    printf("\n Arquivo carregado com sucesso!");
                    carregadoSucesso = 1;
                }else{
                    printf("\n Arquivo nao foi carregado!");
                    carregadoSucesso = 0;
                }
                printf("\n\n");
                break;
            case 2:
                if (carregadoSucesso == 1) {
                    mostraTabuleiro(&c);
                    
                    printf("\n Palavra a ser pesquisada: ");
                    fflush(stdout);
                    scanf("%s", &palavra);
                    fflush(stdout);
                    
                    do{
                        printf("\n **************************");
                        printf("\n * Modo a ser pesquisado: *");
                        printf("\n *  1 - Normal            *");
                        printf("\n *  2 - Analise           *");
                        printf("\n **************************");
                        printf("\n    Escolha uma opcao:");
                        fflush(stdout);
                        scanf("%d", &modo);
                        fflush(stdout);
                    } while (modo < 1 || modo > 2);
                    cacaPalavra(&c, palavra, modo);
                }else{
                    printf("\n Eh necessario carregar um arquivo primeiro!");
                }

                printf("\n\n");
                break;
                
            case 0:
                printf("\n\n");
                printf(" Programa encerrado!");
                return 0;
        }
    }
    
}

