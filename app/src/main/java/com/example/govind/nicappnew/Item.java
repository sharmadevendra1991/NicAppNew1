package com.example.govind.nicappnew;

/**
 * Created by nicsi on 24-08-2017.
 */

public class Item {
    private String OwnerNameText;
    private String RelationName;
    private String ParentsName;
    private String CategoryName;
    private String CastName;
    private String NiwasiName;
    private String LandTypeName;

    public String getOwnerNameText() {
        return OwnerNameText;
    }
    public void setOwnerNameText(String ownerNameText) {
        OwnerNameText = ownerNameText;
    }
    public String getRelationName() {
        return RelationName;
    }
    public void setRelationName(String relationName) {
        RelationName = relationName;
    }
    public String getParentsName() {
        return ParentsName;
    }

    public void setParentsName(String parentsName) {
        ParentsName = parentsName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCastName() {
        return CastName;
    }

    public void setCastName(String castName) {
        CastName = castName;
    }

    public String getNiwasiName() {
        return NiwasiName;
    }

    public void setNiwasiName(String niwasiName) {
        NiwasiName = niwasiName;
    }

    public String getLandTypeName() {
        return LandTypeName;
    }

    public void setLandTypeName(String landTypeName) {
        LandTypeName = landTypeName;
    }

    /*  private String old_Hissa;
        private String new_Hissa;*/
    public Item(String OwnerNameText,String RelationName, String ParentsName, String CategoryName, String CastName, String NiwasiName, String LandTypeName)
    {
        this.OwnerNameText=OwnerNameText;
        this.RelationName=RelationName;
        this.ParentsName=ParentsName;
        this.CategoryName=CategoryName;
        this.CastName=CastName;
        this.NiwasiName=NiwasiName;
        this.LandTypeName=LandTypeName;

        Ischecked=false;
    }


    private boolean Ischecked;


    public boolean ischecked() {
        return Ischecked;
    }
    public void setIschecked(boolean ischecked) {
        Ischecked = ischecked;
    }
   /* public boolean isNabalig() {
        return nabalig;
    }

    public void setNabalig(boolean nabalig) {
        this.nabalig = nabalig;
    }*/

}
