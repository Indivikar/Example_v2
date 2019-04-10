package app.example.Listen.AutocompleteText;


import javafx.scene.Node;

public interface NodeFactory<DataType>
{
    Node createNode(DataType data);
}

