package travel.avg.travel.entities;

import android.graphics.Bitmap;

public class Photo {
    Bitmap photo;

    public Photo() {
    }

    public Photo(Bitmap photo) {
        this.photo = photo;
    }

    public Bitmap getPhoto() {
        return photo;
    }
}
