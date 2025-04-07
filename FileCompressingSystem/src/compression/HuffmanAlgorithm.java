package compression;

import java.util.*;

public class HuffmanAlgorithm {
    private HuffmanNode root;
    private Map<Byte, String> huffmanCodes = new HashMap<>();

    public void buildHuffmanTree(Map<Byte, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode((byte) 0, left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            priorityQueue.add(parent);
        }

        root = priorityQueue.poll();
        generateCodes(root, "");
    }

    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.data, code);
        }
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    public Map<Byte, String> getHuffmanCodes() {
        return huffmanCodes;
    }

    public void buildHuffmanTreeFromCodes(Map<Byte, String> codes) {
        root = new HuffmanNode((byte) 0, 0);
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            insertCode(root, entry.getKey(), entry.getValue());
        }
    }

    private void insertCode(HuffmanNode node, byte data, String code) {
        for (char bit : code.toCharArray()) {
            if (bit == '0') {
                if (node.left == null) node.left = new HuffmanNode((byte) 0, 0);
                node = node.left;
            } else {
                if (node.right == null) node.right = new HuffmanNode((byte) 0, 0);
                node = node.right;
            }
        }
        node.data = data;
    }

    public byte[] decodeBinaryData(byte[] encodedBytes, int dataLength) {
        List<Byte> decodedBytes = new ArrayList<>();
        HuffmanNode currentNode = root;
        int bitIndex = 0;

        for (byte b : encodedBytes) {
            for (int i = 7; i >= 0 && bitIndex < dataLength; i--, bitIndex++) {
                currentNode = ((b >> i) & 1) == 0 ? currentNode.left : currentNode.right;
                if (currentNode.left == null && currentNode.right == null) {
                    decodedBytes.add(currentNode.data);
                    currentNode = root;
                }
            }
        }

        byte[] result = new byte[decodedBytes.size()];
        for (int i = 0; i < decodedBytes.size(); i++) {
            result[i] = decodedBytes.get(i);
        }
        return result;
    }
}
