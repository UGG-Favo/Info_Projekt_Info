public class Inventory {
    private int invLength = 26;
    private Item invArray[] = new Item[invLength];
    private int invFillCounter = 0;

    public Inventory(int length){
        invLength = length;
        fillInvArrayWithNull();
    }

    public void fillInItem(Item itm){
        invArray[invFillCounter] = itm;
        invFillCounter++;
    }

    public void deleteItemFromInventory(int placeInArray){
        if((placeInArray<invLength)&&(placeInArray>=0)) {
            for(int i = placeInArray; i<invLength-1;i++){
                if(i+1<invLength) {
                    invArray[i] = invArray[i + 1];
                }
            }
        }
    }

    public void fillInvArrayWithNull(){

        for(int i=0;i<invLength;i++){
            invArray[i]= new Item(0);
        }

    }

    public int getInvLength() {
        return invLength;
    }

    public void setInvLength(int invLength) {
        this.invLength = invLength;
    }

    public Item[] getInvArray() {
        return invArray;
    }

    public void setInvArray(Item[] invArray) {
        this.invArray = invArray;
    }
}
