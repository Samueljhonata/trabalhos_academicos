#include "sudoku.h"

/*Mostra a matriz*/
void exibe_Quadro(int **quadro,int tam) {
	int linha, coluna;

	for (linha = 0; linha < tam; linha++) {
		for ( coluna = 0; coluna < tam; coluna++) {
			printf("%d", quadro[linha][coluna]);
		}
		printf("\n");
	}

	printf("\n");
}

//Observação a chamada recursiva só é feita se estiver tudo certo,
// senão é voltado as posição como BRANCO.
int resolucao(int **quadro,int tam,int *tentativa,int *recursao) {

	int linha = 0;
	int coluna = 0,num;

    // Procura por uma posição que não tenha atribuição
	if (!localizacao(quadro,tam, &linha, &coluna)){
		return 1;
	}

    // considerar digitos 1 a N
	for ( num = 1; num <= tam; num++ ) {
        // Se for o número
		if (salva_possibilidade(quadro,tam, linha, coluna,num)) {
            //Tentativa
            *tentativa=*tentativa+1;
            // anota o número provisoriamente
			quadro[linha][coluna] = num;
            // Se a linha estiver bem colocada, retornamos
			if (resolucao(quadro,tam,tentativa,recursao)) {
                //chamada recursiva
                *recursao=*recursao+1;
				return 1;
			}
            //tenta novamente
			quadro[linha][coluna] = BRANCO;
		}
	}

	return 0;// refaz as operações
}

/*Verifica a possibilidade em toda a linha*/
int verifica_Linha(int **quadro,int tam, int linha,int num){
	int coluna;

	for (coluna = 0; coluna < tam; coluna++) {
		if (quadro[linha][coluna] == num) {
			return 1;
		}
	}
	return 0;
}
/*Verifica a possibilidade em toda a coluna*/
int verifica_Coluna(int **quadro,int tam, int coluna,int num) {
	int linha;

	for (linha = 0; linha < tam; linha++) {
		if (quadro[linha][coluna] == num) {
			return 1;
		}
	}
	return 0;
}

/*Verifica o quadrado 3x3 para verificar se o número está entre os 9*/
int verifica_Caixa(int **quadro, int Linha_Inicial, int Coluna_Inicial,int num) {
	int linha,coluna;

	for (linha= 0; linha < 3; linha++) {
		for (coluna = 0; coluna < 3; coluna++) {
			if (quadro[linha + Linha_Inicial][coluna + Coluna_Inicial] == num) {
				return 1;
			}
		}
	}
	return 0;
}


/*Verifica se a atribuição é segura para prosseguir*/
int salva_possibilidade(int **quadro,int tam, int linha, int coluna,int num) {
	return !verifica_Linha(quadro,tam, linha,num)
			&& !verifica_Coluna(quadro,tam, coluna,num)
			&& !verifica_Caixa(quadro, linha - (linha % 3), coluna - (coluna %3), num);
}

/*Verifica se há espaço em BRANCO. se tiver, irá retornar o local da coluna como verdadeiro,
se não houver espaço, retonara 0.*/
int localizacao(int **quadro,int tam, int *linha, int *coluna) {

	for (*linha = 0; *linha < tam; (*linha)++) {
		for (*coluna = 0; *coluna < tam; (*coluna)++) {
			if (quadro[*linha][*coluna] == BRANCO) {
				return 1;
			}
		}
	}
	return 0;
}
