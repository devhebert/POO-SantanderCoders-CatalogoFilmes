package Ui;

// Declaração da interface EditItemCallback com um tipo genérico T
public interface EditItemCallback<T> {
    // Declaração do método para remover um item do tipo T
    void remove(T item);

    // Declaração do método para adicionar um novo item do tipo T
    void add(T item);

    // Declaração do método para verificar se um item do tipo T é novo
    boolean isNew(T item);
}
