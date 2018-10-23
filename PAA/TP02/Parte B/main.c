/*
    Wandella Maia de Olivera-2292
    Samuel Jhonata Tavares-2282
*/

#include <stdio.h>
#include <stdlib.h>
#include "sudoku.h"
#include <locale.h> //necessário para usar setlocale

int main() {
    int **mat;
    int recursao=0,tentativa=0;

    //Abertura do arquivo para pegar os dados do sudoku
    char url[]="outa.txt", ch, ch2;
    int linhas=0,coluna=0,i,j,qtdlinha=0,temp;
    FILE *arq,*arqOutro;

    arq = fopen(url, "r");
    arqOutro = fopen(url, "r");

    setlocale(LC_ALL,NULL);
    setlocale(LC_ALL,"");

    if(arq == NULL){
        printf("Erro, nao foi possivel abrir o arquivo\n");
    }
    else{
        while( (ch=fgetc(arq))!= EOF ){
            if(ch == '\n'){
                qtdlinha++;
            }
		}
		qtdlinha++;
	}

    mat = Alocar_matriz_real (qtdlinha, qtdlinha);

        for(i=0;i<qtdlinha;i++){
            for(j=0; j<qtdlinha;j++){
                mat[i][j]=0;
            }
        }

		while( (ch2=fgetc(arqOutro))!= EOF ){
            if(ch2 == '\n'){
                coluna=0;
                linhas++;
            }
            if(ch2 != '\n'){
                temp=ch2-48;
                mat[linhas][coluna]=temp;
                coluna++;

            }

		}
    //mat = Liberar_matriz_real (qtdlinha+1, qtdlinha+1, mat);
	fclose(arq);
    fclose(arqOutro);

    ///Chama o menu principal
    menu(mat,qtdlinha,&recursao,&tentativa);
    printf("END...");
	return 0;
}



void menu(int **mat,int tam,int *recursao,int *tentativa){//Menu de opções

    int escolha,escolha2;

    /*Menu de opções juntamente com a entrada do usuário com a opção de jogo.*/
    do{
        printf("*********************************************************************\n");
        printf("\t\t\t-Bem Vindo ao jogo!-\n");
        printf("\t\t\t 1-SUDOKU\n \t\t\t 0-Sair");
        printf("\n********************************************************************\n");
        scanf("%d",&escolha);

        switch(escolha){
            case 1:
                printf("*********************************************\n");
                printf("\n 1-Modo Normal\n");
                printf("\n 2-Modo Análise\n");
                printf("\n*********************************************\n");

                scanf("%d",&escolha2);

                if(escolha2==1){
                    quadro_Resolver(mat,tam,tentativa,recursao);
                }else{
                    quadro_Resolver(mat,tam,tentativa,recursao);
                    printf("\nQuantidade de tentativas-%d",*tentativa);
                    printf("\nQuantidade de chamadas recursivas-%d\n",*recursao);
                }
                break;
        }

    }while(escolha!=0);

}
