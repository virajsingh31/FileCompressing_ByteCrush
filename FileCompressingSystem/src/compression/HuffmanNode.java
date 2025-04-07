package compression;

public class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    byte data;
    HuffmanNode left, right;

    public HuffmanNode(byte data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = this.right = null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return Integer.compare(this.frequency, node.frequency);
    }
}
