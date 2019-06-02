public class Block {
    public int currentIdx;
    public int nextIdx;

    public Block(int cur,int next){
        this.currentIdx=cur;
        this.nextIdx=next;
    }
    public int getCurrentIdx() {
        return currentIdx;
    }

    public void setCurrentIdx(int currentIdx) {
        this.currentIdx = currentIdx;
    }

    public int getNextIdx() {
        return nextIdx;
    }

    public void setNextIdx(int nextIdx) {
        this.nextIdx = nextIdx;
    }
}