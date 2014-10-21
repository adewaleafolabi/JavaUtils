package JavaUtils;



import models.TableData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wale on 10/21/14.
 * Class generates an HTML table from 
 * a List of Objects that implements 
 * the TableData interface
 */
public class Table {
    private String [] header;
    private List<? extends TableData> tableData;
    private List<String[]> tableDataX = new ArrayList<>();
    private Map<String,String> htmlArgs;


    public Table(String[] header, Map<String,String> htmlArgs, List<? extends TableData> tableData) {
        this.header = header;
        this.htmlArgs=htmlArgs;
        this.tableData = tableData;

        prepData();
    }

    /**
     *Prepares tableData as a List of arrays
     */
    private void prepData(){

        for(int i =0;i<tableData.size();i++){
            tableDataX.add(tableData.get(i).toArray());
        }
    }

    /**
     * Generates the table tag and the html arguments
     * @return String
     */
    private String createOpening(){
        StringBuilder builder = new StringBuilder();
        builder.append("<table ");
        for(Map.Entry<String,String> entry : htmlArgs.entrySet()){
            builder.append(entry.getKey());
            builder.append("='");
            builder.append(entry.getValue());
            builder.append("' ");


        }
        builder.append(">");
        return builder.toString();

    }

    /**
     *Generates the table head section
     * @return String
     */
    private String createHeader(){

        StringBuilder builder = new StringBuilder();
        builder.append("\n\t<thead>\n\t<tr>\n");
        for(String item : this.header){
            builder.append("\t<td>");
            builder.append(item);
            builder.append("</td>\n");

        }
        builder.append("\t</tr>\n</thead>\n");
        return builder.toString();
    }

    /**
     *Generates the table body section
     * @return String
     */
    private String createBody(){
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("<tbody>\n");

        for(String [] item : tableDataX){
            builder.append("\t<tr>\n");
            for(String tdItem : item){
                builder.append("\t\t<td>");
                builder.append(tdItem);
                builder.append("</td>\n");
            }
            builder.append("\t</tr>\n");
        }
        builder.append("</tbody>\n");

        return builder.toString();

    }

    /**
     * Generates the Tables closing tags
     * @return String
     */
    private String createClosing(){
        return "</table>";
    }

    /**
     * Renders the raw table HTML
     * @return String
     */
    public String render(){

        return createOpening() + createHeader() + createBody() +createClosing();
    }



}
