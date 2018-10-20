#include "sudoku.h"

/*Mostra a matriz*/
void exibe_Quadro(int quadro[N][N]) {
	int linha, coluna;
	for (linha = 0; linha < N; linha++) {
		for ( coluna = 0; coluna < N; coluna++) {
			printf("%2d", quadro[linha][coluna]);
		}
		printf("\n");
	}

	printf("\n");
}

/* Utiliza uma grade parcialmente preenchida e tenta atribuir valores a
   todos os locais não atribuídos de forma a satisfazer os requisitos
   para solução Sudoku (não duplicação entre linhas, colunas e caixas) */
   /*Esta fubção busca pela solução possível.
   Funciona da seguinte forma:
   1-Ela tenta colocar possíveis valores na matriz,verificando os requisitos do jogo, ou seja,
    sem repetir números na linha e na coluna.
   */
//Observação a chamada recursiva só é feita se estiver tudo certo,
// senão é voltado as posição como BRANCO.
int resolucao(int quadro[N][N]) {

	int linha = 0;
	int coluna = 0,num;

    // Procura por uma posição que não tenha atribuição
	if (!localizacao(quadro, &linha, &coluna)){
		return 1;
	}

    // considerar digitos 1 a N
	for ( num = 1; num <= N; num++ ) {
        // Se for o número
		if (salva_possibilidade(quadro, linha, coluna, num)) {
            // anota o número provisoriamente
			quadro[linha][coluna] = num;

            // Se a linha estiver bem colocada, retornamos
			if (resolucao(quadro)) {
				return 1;
			}
            //tenta novamente
			quadro[linha][coluna] = BRANCO;
		}
	}

	return 0;// Faz chamada recursiva as operações
}

/*Verifica a possibilidade em toda a linha*/
int verifica_Linha(int quadro[N][N], int linha, int num){
	int coluna;
	for (coluna = 0; coluna < N; coluna++) {
		if (quadro[linha][coluna] == num) {
			return 1;
		}
	}
	return 0;
}
/*Verifica a possibilidade em toda a coluna*/
int verifica_Coluna(int quadro[N][N], int coluna, int num) {
	int linha;
	for (linha = 0; linha < N; linha++) {
		if (quadro[linha][coluna] == num) {
			return 1;
		}
	}
	return 0;
}

/*Verifica o quadrado 3x3 para verificar se o número está entre os 9*/
int verifica_Caixa(int quadro[N][N], int Linha_Inicial, int Coluna_Inicial, int num) {
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
int salva_possibilidade(int quadro[N][N], int linha, int coluna, int num) {
	return !verifica_Linha(quadro, linha, num)
			&& !verifica_Coluna(quadro, coluna, num)
			&& !verifica_Caixa(quadro, linha - (linha % 3), coluna - (coluna %3), num);
}

/*Verifica se há espaço em BRANCO. se tiver, irá retornar o local da coluna como verdadeiro,
se não houver espaço, retonara 0.*/
int localizacao(int quadro[N][N], int *linha, int *coluna) {

	for (*linha = 0; *linha < N; (*linha)++) {
		for (*coluna = 0; *coluna < N; (*coluna)++) {
			if (quadro[*linha][*coluna] == BRANCO) {
				return 1;
			}
		}
	}
	return 0;
}
