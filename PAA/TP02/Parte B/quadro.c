#include "sudoku.h"
#include "quadro.h"

int quadro_Resolver(int **quadro,int tam,int *tentativa,int *recursao){
    int temp;

    printf("\n-------------QUADRO INICIAL-----------\n");
    exibe_Quadro(quadro,tam);

    temp = resolucao(quadro,tam,tentativa,recursao);
    resultado(temp,quadro,tam,tentativa,recursao);

}

/*Função que decide o resultado*/
void resultado(int resulta,int **quadro,int tam, int *tentativa,int *recursao){
    /*Recebe 1 se há solucao e 0 se não*/
	if (resulta) {
        printf("\n---------------SUDOKU RESOLVIDO-----------\n");

		exibe_Quadro(quadro,tam);
	} else {
		printf("\n------------Não há solucao!----------------\n");

        exibe_Quadro(quadro,tam);
	}
}
