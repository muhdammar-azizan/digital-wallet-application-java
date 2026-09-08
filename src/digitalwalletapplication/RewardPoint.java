//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022


package digitalwalletapplication;

class RewardPoint {
    private int points;

    public RewardPoint() {
        this.points = 0;
    }

    public void addPoint(int point) {
        points += point;
        System.out.println(point + " points added. Current points: " + points);
    }

    public void checkPointBalance() {
        System.out.println("Current reward points: " + points);
    }
}