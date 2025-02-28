import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {
    public int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE]; // 2D array for board
    private String[][] piecesArray; //2D array = image::HP::board position

    public GameBoard() {
        setTitle("Chess Board");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE)); // Use GridLayout for the board layout

        // Initialize the 2D array of panels
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel();
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(Color.BLACK); // Black squares
                } else {
                    squares[row][col].setBackground(Color.WHITE); // White squares
                }
                add(squares[row][col]); // Add each square to the board
            }
        }

        // Initialize chess pieces array
        piecesArray = new String[32][3];  
        loadPieces();

        // Sort images using Merge Sort before placing them on the board
        mergeSort(piecesArray, 0, piecesArray.length - 1);

        // Populate the board with sorted pieces
        populateBoard();
    }

    /** Merge Sort Implementation */
    private void mergeSort(String[][] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Recursively divide array
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Merge sorted halves
            merge(array, left, mid, right);
        }
    }

    /** Merge two sorted subarrays */
    private void merge(String[][] array, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        // Temporary arrays
        String[][] leftArray = new String[leftSize][3];
        String[][] rightArray = new String[rightSize][3];

        // Copy data into temporary arrays
        for (int i = 0; i < leftSize; i++)
            leftArray[i] = array[left + i];
        for (int j = 0; j < rightSize; j++)
            rightArray[j] = array[mid + 1 + j];

        // Merge back to original array
        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (Integer.parseInt(leftArray[i][2]) <= Integer.parseInt(rightArray[j][2])) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements
        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private void populateBoard() {
        int pieceRow = 0;
        int squareName = 1; // all squares numbered 1-64

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (pieceRow < piecesArray.length) {
                    int piecePosition = Integer.parseInt(piecesArray[pieceRow][2]);

                    if (squareName == piecePosition) {
                        String imagePath = piecesArray[pieceRow][0];
                        String hpText = piecesArray[pieceRow][1];

                        ImageIcon icon = new ImageIcon(imagePath);
                        Image scaledImage = icon.getImage().getScaledInstance(40, 42, Image.SCALE_SMOOTH);

                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                        JLabel textLabel = new JLabel(hpText, SwingConstants.CENTER);
                        textLabel.setForeground(Color.BLACK);

                        JPanel piecePanel = new JPanel(new BorderLayout());
                        piecePanel.setOpaque(false);
                        piecePanel.add(pieceLabel, BorderLayout.CENTER);
                        piecePanel.add(textLabel, BorderLayout.SOUTH);

                        squares[row][col].setLayout(new BorderLayout());
                        squares[row][col].add(piecePanel, BorderLayout.CENTER);

                        pieceRow++;
                    }
                }
                squareName++;
            }
        }

        revalidate();
        repaint();
    }

    private void loadPieces() {
        // Initialize chess pieces
        piecesArray[0][0] = "chess1.png"; piecesArray[0][1] = "HP:1"; piecesArray[0][2]="8";
        piecesArray[1][0] = "chess 2.png"; piecesArray[1][1] = "HP:1"; piecesArray[1][2]="7";
        piecesArray[2][0] = "chess 3.png"; piecesArray[2][1] = "HP:1"; piecesArray[2][2]="6";
        piecesArray[3][0] = "chess 4.png"; piecesArray[3][1] = "HP:1"; piecesArray[3][2]="5";
        piecesArray[4][0] = "chess 5.png"; piecesArray[4][1] = "HP:1"; piecesArray[4][2]="4";
        piecesArray[5][0] = "chess 6.png"; piecesArray[5][1] = "HP:1"; piecesArray[5][2]="3";
        piecesArray[6][0] = "chess1.png"; piecesArray[6][1] = "HP:1"; piecesArray[6][2]="2";
        piecesArray[7][0] = "chess 2.png"; piecesArray[7][1] = "HP:1"; piecesArray[7][2]="1";
        piecesArray[8][0] = "chess 3.jpg"; piecesArray[8][1] = "HP:1"; piecesArray[8][2]="57";
        piecesArray[9][0] = "chess 4.png"; piecesArray[9][1] = "HP:1"; piecesArray[9][2]="58";
        piecesArray[10][0] = "chess 5.png"; piecesArray[10][1] = "HP:1"; piecesArray[10][2]="59";
        piecesArray[11][0] = "chess 6.png"; piecesArray[11][1] = "HP:1"; piecesArray[11][2]="60";
        piecesArray[12][0] = "black 1.png"; piecesArray[12][1] = "HP:1"; piecesArray[12][2]="61";
        piecesArray[13][0] = "black 2.png"; piecesArray[13][1] = "HP:1"; piecesArray[13][2]="62";
        piecesArray[14][0] = "black 3.png"; piecesArray[14][1] = "HP:1"; piecesArray[14][2]="63";
        piecesArray[15][0] = "black 4.png"; piecesArray[15][1] = "HP:1"; piecesArray[15][2]="64";
        piecesArray[16][0] = "black 5.png"; piecesArray[16][1] = "HP:5"; piecesArray[16][2]="9";
        piecesArray[17][0] = "black 6.png"; piecesArray[17][1] = "HP:3"; piecesArray[17][2]="10";
        piecesArray[18][0] = "chess1.png"; piecesArray[18][1] = "HP:3"; piecesArray[18][2]="11";
        piecesArray[19][0] = "chess 2.png"; piecesArray[19][1] = "HP:9"; piecesArray[19][2]="12";
        piecesArray[20][0] = "chess 3.png"; piecesArray[20][1] = "HP:100"; piecesArray[20][2]="13";
        piecesArray[21][0] = "chess 4.png"; piecesArray[21][1] = "HP:3"; piecesArray[21][2]="14";
        piecesArray[22][0] = "chess 5.png"; piecesArray[22][1] = "HP:3"; piecesArray[22][2]="15";
        piecesArray[23][0] = "chess 6.png"; piecesArray[23][1] = "HP:5"; piecesArray[23][2]="16";
        piecesArray[24][0] = "black 1.png"; piecesArray[24][1] = "HP:5"; piecesArray[24][2]="49";
        piecesArray[25][0] = "black 2.png"; piecesArray[25][1] = "HP:3"; piecesArray[25][2]="50";
        piecesArray[26][0] = "black 1.png"; piecesArray[26][1] = "HP:3"; piecesArray[26][2]="51";
        piecesArray[27][0] = "black 2.png"; piecesArray[27][1] = "HP:9"; piecesArray[27][2]="52";
        piecesArray[28][0] = "black 3.png"; piecesArray[28][1] = "HP:100"; piecesArray[28][2]="53";
        piecesArray[29][0] = "black 4.png"; piecesArray[29][1] = "HP:3"; piecesArray[29][2]="54";
        piecesArray[30][0] = "black 5.png"; piecesArray[30][1] = "HP:3"; piecesArray[30][2]="55";
        piecesArray[31][0] = "black 6.png"; piecesArray[31][1] = "HP:5"; piecesArray[31][2]="56";
        piecesArray[31][0] = "black 6.png"; piecesArray[31][1] = "HP:5"; piecesArray[31][2]="56";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}
