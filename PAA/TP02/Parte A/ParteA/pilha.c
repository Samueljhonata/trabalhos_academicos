#include "pilha.h"

void FPVazia(TipoPilha *Pilha) {
    Pilha->Topo = (TipoApontador) malloc(sizeof (TipoCelula));
    Pilha->Fundo = Pilha->Topo;
    Pilha->Topo->Prox = NULL;
    Pilha->Tamanho = 0;
}

int Vazia(TipoPilha Pilha) {
    return (Pilha.Topo == Pilha.Fundo);
}

void Empilha(TipoItem x, TipoPilha *Pilha) {
    TipoApontador Aux;
    Aux = (TipoApontador) malloc(sizeof (TipoCelula));
    Pilha->Topo->Item = x;
    Aux->Prox = Pilha->Topo;
    Pilha->Topo = Aux;
    Pilha->Tamanho++;
    //printf("******************************EMPILHOU ");
}

void Desempilha(TipoPilha *Pilha, TipoItem *Item) {
    TipoApontador q;
    if (Vazia(*Pilha)) {
        printf("Erro: lista vazia\n");
        return;
    }
    q = Pilha->Topo;
    Pilha->Topo = q->Prox;
    *Item = q->Prox->Item;
    free(q);
    Pilha->Tamanho--;
    //printf("******************************DESEMPILHOU ");
}

int Tamanho(TipoPilha Pilha) {
    return (Pilha.Tamanho);
}

void Imprime(TipoPilha Pilha)
{ TipoApontador Aux;
  Aux = Pilha.Topo->Prox;
  while (Aux != NULL) 
    { printf("[%d][%d]\n", Aux->Item.posicao.linha, Aux->Item.posicao.coluna);
      Aux = Aux -> Prox;
    }
}

void DesempilhaSobras(TipoPilha *Pilha){ 
    TipoApontador Aux;
    TipoItem tItem;
    Aux = Pilha->Topo->Prox;
    while (Aux->Item.fim == 0) { 
        Desempilha(Pilha, &tItem);
        Aux = Aux->Prox;
        if(Aux == NULL){
            break;
        }
    }
}