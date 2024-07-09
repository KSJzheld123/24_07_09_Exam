package org.koreait;

public class MotivationList {

    int id;
    String body;
    String source;
    String date;

    MotivationList(int id, String body, String source, String date) {
        this.id = id;
        this.body = body;
        this.source = source;
        this.date = date;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getBody() {
        return body;
    }

    private void setBody(String body) {
        this.body = body;
    }

    private String getSource() {
        return source;
    }

    private void setSource(String source) {
        this.source = source;
    }

    private String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }


}
