import java.util.Scanner;
class MissileControlSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Missile Type: ");
        String type = sc.nextLine();
        System.out.print("Missile ID: ");
        String missileId = sc.nextLine();
        System.out.print("Threat Level: ");
        int threatLevel = sc.nextInt();
        System.out.print("Clearance: ");
        boolean clearance = sc.nextBoolean();
        Missile missile = null;
        switch (type) {
            case "NuclearMissile":
                missile = new NuclearMissile(missileId, 10000);
                break;
            case "TorpedoMissile":
                missile = new TorpedoMissile(missileId, 3000);
                break;
            case "DefenseInterceptor":
                missile = new DefenseInterceptor(missileId, 1500);
                break;
            default:
                System.out.println("Invalid Missile Type");
                return;
        }
        boolean status = missile.launch(threatLevel, clearance);
        System.out.println("\nMissile ID: " + missileId);
        if (status) {
            System.out.println("Launch Status: APPROVED");
            if (missile instanceof DefenseInterceptor) {
                System.out.println("Interceptor launched successfully");
            } else {
                System.out.println("Missile launched successfully");
            }
        } else {
            System.out.println("Launch Status: DENIED");
            if (missile instanceof NuclearMissile && threatLevel < 9) {
                System.out.println("Reason: Threat level insufficient for nuclear launch");
            } else if (missile instanceof NuclearMissile && !clearance) {
                System.out.println("Reason: Command clearance required");
            } else {
                System.out.println("Reason: Launch conditions not met");
            }
        }
    }
}
abstract class Missile {
    String missileId;
    int range;
    Missile(String missileId, int range) {
        this.missileId=missileId;
        this.range=range;

    }
    abstract boolean launch(int threatLevel,boolean hasClearance);
}
class NuclearMissile extends Missile{
    NuclearMissile(String missileId,int range){
        super(missileId,range);

    }
    @Override
    boolean launch(int threatLevel, boolean hasClearance){
        return threatLevel >= 9 && hasClearance;
    }
}


class TorpedoMissile extends Missile {

    TorpedoMissile(String missileId, int range) {
        super(missileId, range);
    }
    @Override
    boolean launch(int threatLevel, boolean hasClearance) {
        return threatLevel >= 5;
    }
}
class DefenseInterceptor extends Missile {

    DefenseInterceptor(String missileId, int range) {
        super(missileId, range);
    }
    @Override
    boolean launch(int threatLevel, boolean hasClearance) {
        return threatLevel >= 3;
    }
}


