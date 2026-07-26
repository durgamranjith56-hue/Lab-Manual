import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. ENUMS & BASIC MODELS
// ==========================================

enum Color {
    WHITE, BLACK
}

class Spot {
    private final int x;
    private final int y;
    private Piece piece;

    public Spot(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }
}

// ==========================================
// 2. ABSTRACT PIECE & CONCRETE PIECES
// ==========================================

abstract class Piece {
    private boolean killed = false;
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() { return color; }
    public boolean isKilled() { return killed; }
    public void setKilled(boolean killed) { this.killed = killed; }

    // Abstract method overridden by subclasses (Polymorphism)
    public abstract boolean canMove(Board board, Spot start, Spot end);
    public abstract String getSymbol();
}

class Pawn extends Piece {
    public Pawn(Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) {
            return false;
        }

        int direction = (getColor() == Color.WHITE) ? -1 : 1;
        int dx = end.getX() - start.getX();
        int dy = Math.abs(end.getY() - start.getY());

        // Standard forward move
        if (dy == 0 && dx == direction && end.getPiece() == null) {
            return true;
        }
        // Diagonal capture
        if (dy == 1 && dx == direction && end.getPiece() != null) {
            return true;
        }
        return false;
    }

    @Override
    public String getSymbol() { return getColor() == Color.WHITE ? "P" : "p"; }
}

class Knight extends Piece {
    public Knight(Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) {
            return false;
        }
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return dx * dy == 2;
    }

    @Override
    public String getSymbol() { return getColor() == Color.WHITE ? "N" : "n"; }
}

class Bishop extends Piece {
    public Bishop(Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) {
            return false;
        }
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return dx == dy;
    }

    @Override
    public String getSymbol() { return getColor() == Color.WHITE ? "B" : "b"; }
}

class Rook extends Piece {
    public Rook(Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) {
            return false;
        }
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return (dx == 0 && dy > 0) || (dx > 0 && dy == 0);
    }

    @Override
    public String getSymbol() { return getColor() == Color.WHITE ? "R" : "r"; }
}

class Queen extends Piece {
    public Queen(Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) {
            return false;
        }
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return (dx == dy) || (dx == 0 && dy > 0) || (dx > 0 && dy == 0);
    }

    @Override
    public String getSymbol() { return getColor() == Color.WHITE ? "Q" : "q"; }
}

class King extends Piece {
    public King(Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) {
            return false;
        }
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return dx <= 1 && dy <= 1;
    }

    @Override
    public String getSymbol() { return getColor() == Color.WHITE ? "K" : "k"; }
}

// ==========================================
// 3. MOVE & BOARD IMPLEMENTATION
// ==========================================

class Move {
    private final Spot start;
    private final Spot end;
    private final Piece pieceMoved;
    private Piece pieceKilled;

    public Move(Spot start, Spot end) {
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }

    public Spot getStart() { return start; }
    public Spot getEnd() { return end; }
    public Piece getPieceMoved() { return pieceMoved; }
    public Piece getPieceKilled() { return pieceKilled; }
    public void setPieceKilled(Piece pieceKilled) { this.pieceKilled = pieceKilled; }
}

class Board {
    private final Spot[][] boxes = new Spot[8][8];

    public Board() {
        this.resetBoard();
    }

    public Spot getBox(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return boxes[x][y];
    }

    public final void resetBoard() {
        // Initialize Black pieces (row 0 & 1)
        boxes[0][0] = new Spot(0, 0, new Rook(Color.BLACK));
        boxes[0][1] = new Spot(0, 1, new Knight(Color.BLACK));
        boxes[0][2] = new Spot(0, 2, new Bishop(Color.BLACK));
        boxes[0][3] = new Spot(0, 3, new Queen(Color.BLACK));
        boxes[0][4] = new Spot(0, 4, new King(Color.BLACK));
        boxes[0][5] = new Spot(0, 5, new Bishop(Color.BLACK));
        boxes[0][6] = new Spot(0, 6, new Knight(Color.BLACK));
        boxes[0][7] = new Spot(0, 7, new Rook(Color.BLACK));

        for (int j = 0; j < 8; j++) {
            boxes[1][j] = new Spot(1, j, new Pawn(Color.BLACK));
        }

        // Initialize White pieces (row 6 & 7)
        for (int j = 0; j < 8; j++) {
            boxes[6][j] = new Spot(6, j, new Pawn(Color.WHITE));
        }

        boxes[7][0] = new Spot(7, 0, new Rook(Color.WHITE));
        boxes[7][1] = new Spot(7, 1, new Knight(Color.WHITE));
        boxes[7][2] = new Spot(7, 2, new Bishop(Color.WHITE));
        boxes[7][3] = new Spot(7, 3, new Queen(Color.WHITE));
        boxes[7][4] = new Spot(7, 4, new King(Color.WHITE));
        boxes[7][5] = new Spot(7, 5, new Bishop(Color.WHITE));
        boxes[7][6] = new Spot(7, 6, new Knight(Color.WHITE));
        boxes[7][7] = new Spot(7, 7, new Rook(Color.WHITE));

        // Initialize empty spaces (rows 2 to 5)
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }
        }
    }

    public void printBoard() {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                Piece piece = boxes[i][j].getPiece();
                if (piece == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(piece.getSymbol() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

// ==========================================
// 4. GAME ENGINE & MAIN EXECUTION
// ==========================================

public class ChessGame {
    private final Board board;
    private Color currentTurn;
    private final List<Move> movesPlayed;

    public ChessGame() {
        this.board = new Board();
        this.currentTurn = Color.WHITE;
        this.movesPlayed = new ArrayList<>();
    }

    public boolean makeMove(int startX, int startY, int endX, int endY) {
        Spot startSpot = board.getBox(startX, startY);
        Spot endSpot = board.getBox(endX, endY);
        Piece playerPiece = startSpot.getPiece();

        // 1. Validation checks
        if (playerPiece == null) {
            System.out.println("Error: No piece at start position.");
            return false;
        }

        if (playerPiece.getColor() != currentTurn) {
            System.out.println("Error: It is " + currentTurn + "'s turn.");
            return false;
        }

        if (!playerPiece.canMove(board, startSpot, endSpot)) {
            System.out.println("Error: Invalid move for " + playerPiece.getClass().getSimpleName() + ".");
            return false;
        }

        // 2. Perform Move
        Move move = new Move(startSpot, endSpot);
        Piece destinationPiece = endSpot.getPiece();
        if (destinationPiece != null) {
            destinationPiece.setKilled(true);
            move.setPieceKilled(destinationPiece);
            System.out.println(playerPiece.getSymbol() + " captured " + destinationPiece.getSymbol() + "!");
        }

        endSpot.setPiece(playerPiece);
        startSpot.setPiece(null);
        movesPlayed.add(move);

        // 3. Switch Turn
        this.currentTurn = (this.currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
        return true;
    }

    public Board getBoard() { return board; }

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        System.out.println("--- Initial Chess Board ---");
        System.out.println("Uppercase = WHITE, Lowercase = BLACK");
        game.getBoard().printBoard();

        // Turn 1: White Pawn moves from (6,4) to (5,4)
        System.out.println("Move 1: WHITE Pawn moves (6,4) -> (5,4)");
        game.makeMove(6, 4, 5, 4);
        game.getBoard().printBoard();

        // Turn 2: Black Pawn moves from (1,4) to (2,4)
        System.out.println("Move 2: BLACK Pawn moves (1,4) -> (2,4)");
        game.makeMove(1, 4, 2, 4);
        game.getBoard().printBoard();

        // Turn 3: White Knight moves from (7,6) to (5,5)
        System.out.println("Move 3: WHITE Knight moves (7,6) -> (5,5)");
        game.makeMove(7, 6, 5, 5);
        game.getBoard().printBoard();
    }
}
