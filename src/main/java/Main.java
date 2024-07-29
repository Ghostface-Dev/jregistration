import data.Entity;
import data.LocalData;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        LocalData data = new LocalData("C:\\Users\\gkill\\Documents\\GitHub\\registro\\datas");

        Entity entity = new Entity(34);

        data.writeEntity(entity);
        System.out.println(data.getEntity(34));

    }
}
