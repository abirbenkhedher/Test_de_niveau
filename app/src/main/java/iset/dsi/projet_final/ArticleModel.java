package iset.dsi.projet_final;

import android.os.Parcel;
import android.os.Parcelable;

public class ArticleModel implements Parcelable {
    private String name;
    private String price;
   private String ArticleId;

   public ArticleModel(){

   }

    public ArticleModel(String name, String price, String articleId) {
        this.name = name;
        this.price = price;
        ArticleId = articleId;
    }

    protected ArticleModel(Parcel in) {
        name = in.readString();
        price = in.readString();
        ArticleId = in.readString();
    }

    public static final Creator<ArticleModel> CREATOR = new Creator<ArticleModel>() {
        @Override
        public ArticleModel createFromParcel(Parcel in) {
            return new ArticleModel(in);
        }

        @Override
        public ArticleModel[] newArray(int size) {
            return new ArticleModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getArticleId() {
        return ArticleId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(ArticleId);
    }
}
