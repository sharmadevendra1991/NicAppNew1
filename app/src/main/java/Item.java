/**
 * Created by nicsi on 21-08-2017.
 */

public class Item {
    private String ownername;
    private boolean nabalig;
    private String relation;
    private String relative;
    private boolean Ischecked;



    public Item(String ownername, String relation, String relative, boolean nabalig)
{
    this.ownername=ownername;
    this.nabalig=nabalig;
    this.relation=relation;
    this.relative=relative;
    Ischecked=false;
}

    public boolean ischecked() {
        return Ischecked;
    }

    public void setIschecked(boolean ischecked) {
        Ischecked = ischecked;
    }
    public boolean isNabalig() {
        return nabalig;
    }

    public void setNabalig(boolean nabalig) {
        this.nabalig = nabalig;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }
}
