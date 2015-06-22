package ch.swissonid.design_lib_sample.util;

/**
 * Created by ramakrishna.s on 6/22/2015.
 */
public class ParvamObj {

    private int _id;
    private String parvamName;
    private String parvamChapter;
    private int pravamChapterID;

    public ParvamObj(){

    }

    public ParvamObj(String parvamName,String parvamChapter,int pravamChapterID){
        this.parvamName = parvamName;
        this.parvamChapter = parvamChapter;
        this.pravamChapterID = pravamChapterID;
    }

    public ParvamObj(String parvamName,String parvamChapter){
        this.parvamName = parvamName;
        this.parvamChapter = parvamChapter;
        this.pravamChapterID = 0;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getParvamName() {
        return parvamName;
    }

    public void setParvamName(String parvamName) {
        this.parvamName = parvamName;
    }

    public String getParvamChapter() {
        return parvamChapter;
    }

    public void setParvamChapter(String parvamChapter) {
        this.parvamChapter = parvamChapter;
    }

    public int getPravamChapterID() {
        return pravamChapterID;
    }

    public void setPravamChapterID(int pravamChapterID) {
        this.pravamChapterID = pravamChapterID;
    }

}
