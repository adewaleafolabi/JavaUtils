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

    private final String[] header;
    private final List<? extends TableData> tableData;
    private final List<String[]> tableDataX = new ArrayList<>();
    private final Map<String, String> htmlArgs;
    private final String TAG_NEW_LINE = "\n";
    private final String TAB = "\t";
    private final String TAG_TBODY_OPEN = "<tbody>";
    private final String TAG_TBODY_CLOSE = "</tbody>";
    private final String TAG_TH_OPEN = "<thead>";
    private final String TAG_TH_CLOSE = "</thead>";
    private final String TAG_TR_OPEN = "<tr>";
    private final String TAG_TR_CLOSE = "</tr>";
    private final String TAG_TD_OPEN = "<td>";
    private final String TAG_TD_CLOSE = "</td>";
    private final String TAG_NBSPC = "&nbsp;";
    private int tdCount = 0;


    public Table(String[] header, Map<String, String> htmlArgs, List<? extends TableData> tableData) {
        this.header = header;
        this.htmlArgs = htmlArgs;
        this.tableData = tableData;
        this.tdCount = this.header.length;

        prepData();
    }

    /**
     * Prepares tableData as a List of arrays
     */
    private void prepData() {

        for (int i = 0; i < tableData.size(); i++) {
            tableDataX.add(tableData.get(i).toArray());
        }
    }

    /**
     * Generates the table tag and the html arguments
     *
     * @return String
     */
    private String createOpening() {
        StringBuilder builder = new StringBuilder();
        builder.append("<table ");
        for (Map.Entry<String, String> entry : htmlArgs.entrySet()) {
            builder.append(entry.getKey());
            builder.append("='");
            builder.append(entry.getValue());
            builder.append("' ");


        }
        builder.append(">");
        return builder.toString();

    }

    /**
     * Generates the table head section
     *
     * @return String
     */
    private String createHeader() {

        StringBuilder builder = new StringBuilder();
        builder.append(TAG_NEW_LINE)
                .append(TAB)
                .append(TAG_TH_OPEN)
                .append(TAG_NEW_LINE)
                .append(TAB)
                .append(TAG_TR_OPEN)
                .append(TAG_NEW_LINE);


        for (String item : this.header) {
            builder.append(TAB)
                    .append(TAG_TD_OPEN)
                    .append(item)
                    .append(TAG_TD_CLOSE)
                    .append(TAG_NEW_LINE);

        }
        builder.append(TAB)
                .append(TAG_TR_CLOSE)
                .append(TAG_NEW_LINE)
                .append(TAG_TH_CLOSE)
                .append(TAG_NEW_LINE);
        return builder.toString();
    }

    /**
     * Generates the table body section
     *
     * @return String
     */
    private String createBody() {
        StringBuilder builder = new StringBuilder();
        builder.append(TAG_NEW_LINE)
                .append(TAG_TBODY_OPEN)
                .append(TAG_NEW_LINE);

        for (String[] item : tableDataX) {
            builder.append(TAB)
                    .append(TAG_TR_OPEN)
                    .append(TAG_NEW_LINE);

            //Using the length of the header array in order
            // to ensure that the TD generated matches
            // the column size

            for (int i = 0; i < header.length; i++) {
                builder.append(TAB)
                        .append(TAB)
                        .append(TAG_TD_OPEN);
                try {
                    builder.append(item[i]);
                } catch (Exception exp) {
                    builder.append(TAG_NBSPC);
                }
                builder.append(TAG_TD_CLOSE)
                        .append(TAG_NEW_LINE);
            }
            builder.append(TAB)
                    .append(TAG_TR_CLOSE)
                    .append(TAG_NEW_LINE);
        }
        builder.append(TAG_TBODY_CLOSE).append(TAG_NEW_LINE);

        return builder.toString();

    }

    /**
     * Generates the Tables closing tags
     *
     * @return String
     */
    private String createClosing() {
        return "</table>";
    }

    /**
     * Renders the raw table HTML
     *
     * @return String
     */
    public String render() {

        return createOpening() + createHeader() + createBody() + createClosing();
    }


}

