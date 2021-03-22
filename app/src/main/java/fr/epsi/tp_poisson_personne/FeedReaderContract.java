package fr.epsi.tp_poisson_personne;

import android.provider.BaseColumns;

public class FeedReaderContract {

    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME_NOM = "nom";
        public static final String COLUMN_NAME_PRENOM = "prenom";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_GROUPE = "groupe";
        public static final String COLUMN_NAME_PHOTO = "photo";
    }
}
