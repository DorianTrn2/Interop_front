module hr.algebra.dtarin.interop_frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;


    opens hr.algebra.dtarin.interop_frontend to javafx.fxml;
    exports hr.algebra.dtarin.interop_frontend;
}