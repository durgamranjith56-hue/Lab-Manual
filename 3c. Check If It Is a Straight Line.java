class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        // First two points define the base slope
        int x0 = coordinates[0][0];
        int y0 = coordinates[0][1];
        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];

        int dx = x1 - x0;
        int dy = y1 - y0;

        // Check all other points against the base slope
        for (int i = 2; i < coordinates.length; i++) {
            int x = coordinates[i][0];
            int y = coordinates[i][1];

            // Cross-multiplication check: dy * (x - x0) == dx * (y - y0)
            if (dy * (x - x0) != dx * (y - y0)) {
                return false;
            }
        }

        return true;
    }
}
