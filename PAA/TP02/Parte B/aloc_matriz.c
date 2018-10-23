#include "aloc_matriz.h"
#include <stdio.h>
#include <stdlib.h>

int **Alocar_matriz_real (int m, int n) {
    int **v; /* ponteiro para a matriz */
    int i; /* variavel auxiliar */
    if (m < 1 || n < 1) { /* verifica parametros recebidos */
        printf ("** Erro: Parametro invalido **\n");
        return (NULL);
    }
    /* aloca as linhas da matriz */
    v = (int **) calloc (m, sizeof(int *));
    if (v == NULL) {
        printf ("** Erro: Memoria Insuficiente **");
        return (NULL);
    }
    /* aloca as colunas da matriz */
    for ( i = 0; i < m; i++ ) {
        v[i] = (int*) calloc (n, sizeof(int));
        if (v[i] == NULL) {
            printf ("** Erro: Memoria Insuficiente **");
            return (NULL);
        }
    }
    return (v); /* retorna o ponteiro para a matriz */
}

int **Liberar_matriz_real (int m, int n, int **v) {
    int i; /* variavel auxiliar */
    if (v == NULL) return (NULL);
    if (m < 1 || n < 1) { /* verifica parametros recebidos */
        printf ("** Erro: Parametro invalido **\n");
        return (v);
    }
    for (i = 0; i < m; i++) free (v[i]); /* libera as linhas da matriz */
    free (v); /* libera a matriz */
    return (NULL); /* retorna um ponteiro nulo */
}

