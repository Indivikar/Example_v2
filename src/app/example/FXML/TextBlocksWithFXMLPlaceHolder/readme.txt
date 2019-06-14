TextBolcks (Textbausteine)

hiermit kann man Nodes in der FXML einen Platzhalter(PlasceHolder) mitgeben und
mit Hilfe einer Liste oder einer Datenbank, den Platzhalter durch einen anderen Text ersetzen.

Platzhalter können für folgende Elemente gesetzt werden:
- Text
- PromptText
- ToolTip

Besonderheit
--------------
Wenn kein Tooltip gesetzt wurde, kann in der Liste ein Text für den ToolTip hinterlegt werden,
wenn der Getter "getToolTip()" in der Klasse "TextBlock" nicht NULL ist, wird der ToolTip automatisch gesetzt

