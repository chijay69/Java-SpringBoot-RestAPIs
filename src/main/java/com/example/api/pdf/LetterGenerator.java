

package com.example.api.pdf;

import com.example.api.model.data.Location;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@Slf4j
public class LetterGenerator {
    public void generateLetter(String outputPath, String name, String accountNumber, Location location, String country) {
        try {
            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 12;
            float fontHeight = fontSize;
            float leading = 15;

            String title = "LETTER OF INTRODUCTION - " + name + " (" + accountNumber + ")";
            float titleWidth = font.getStringWidth(title) / 1000 * fontSize;
            float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // START GREETINGS
            contentStream.setFont(font, fontSize);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd'th' MMMM yyyy", Locale.ENGLISH);

            String currentDate = dateFormat.format(new Date());

            float yCordinate = page.getCropBox().getUpperRightY() - 30;
            float startX = page.getCropBox().getLowerLeftX() + 30;
            float endX = page.getCropBox().getUpperRightX() - 30;

            contentStream.beginText();
            contentStream.newLineAtOffset(startX, yCordinate);

            contentStream.showText(" ");
            yCordinate -= fontHeight;  //This line is to track the yCordinate
            contentStream.newLineAtOffset(0, -leading);

            yCordinate -= leading;

            contentStream.showText(currentDate);
            yCordinate -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);

            yCordinate -= leading + 10;


            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(startX, yCordinate);

            contentStream.showText("The Consul General");
            yCordinate -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);

            yCordinate -= leading;

            contentStream.showText(location.getAddressedTo() +" of "+ country);
//            contentStream.showText("The Deputy High Commission of Canada");
            yCordinate -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);

            yCordinate -= leading;

            contentStream.showText(location.getAddress());
//            contentStream.showText("4, Anifowoshe Street");
            yCordinate -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);
            yCordinate -= leading;

            contentStream.showText(location.getCityDistrict());
//            contentStream.showText("Victoria Island");
            yCordinate -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);
            yCordinate -= leading;

//            contentStream.showText("Lagos State");
            contentStream.showText(location.getState());
            yCordinate -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);
            yCordinate -= leading;

            contentStream.showText("Nigeria.");
            yCordinate -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);

            yCordinate -= leading;

            //trying to loop throught

            contentStream.endText();


            float yCordinate1 = page.getCropBox().getUpperRightY() - 240;

            contentStream.beginText();

            contentStream.newLineAtOffset(startX, yCordinate1+leading);

            contentStream.showText("Dear Sir,");
            yCordinate -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);

            contentStream.showText(" ");
            yCordinate -= fontHeight;  //This line is to track the yCordinate
            contentStream.newLineAtOffset(0, -leading);

            yCordinate -= leading;

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            yCordinate1 -= leading;

            contentStream.showText(title);
            yCordinate1 -= fontHeight;
            contentStream.newLineAtOffset(startX, -leading);

            contentStream.endText(); // End of text mode


            float underlineStartX = startX;
            float underlineEndX = startX + titleWidth + 2;
            float underlineY = yCordinate1 + titleHeight - fontHeight + 5; // Adjust this value to set the underline position

            // Draw a line to underline the title
            contentStream.moveTo(underlineStartX, underlineY);
            contentStream.lineTo(underlineEndX, underlineY);
            contentStream.stroke();
            yCordinate1 -= leading;

            contentStream.beginText();

            contentStream.newLineAtOffset(startX, yCordinate1);
            contentStream.endText();
            // END GREETINGS

            // START MAIN BODY

            float startX1 = page.getCropBox().getLowerLeftX() + 30;
            float endX1= page.getCropBox().getUpperRightX() - 30;

            contentStream.setFont(font, fontSize); // Set font here

            float yCordinate2 = yCordinate1 - 20; // Set y axis coordinate

            contentStream.beginText();
            contentStream.newLineAtOffset(startX1, yCordinate2+leading);


            String text = """
We refer to the above subject matter.

Kindly note that Mr. Alozie Chijindu Victor is registered with CrusaderSterling Pensions Limited, and his account is funded till date.

By regulations (Pensions Reform Act 2014), his contributions can be accessed upon retirement.

However, where he voluntarily disengages from employment before the age of 50 years, he is eligible to withdraw an amount not exceeding 25% of his balance, after four months of disengagement.

Kindly accord him all the necessary assistance.""";

            // Splitting text into paragraphs
            String[] paragraphs = text.split("(?<=\\.\\n)");

            for (String paragraph : paragraphs) {
                String[] words = paragraph.split("\\s+");
                StringBuilder line = new StringBuilder();

                for (String word : words) {
                    float width = font.getStringWidth(line.toString() + word) / 1000 * fontSize;

                    if (width < (endX1 - startX1)) {
                        line.append(word).append(" ");
                    } else {
                        float spaceWidth = font.getStringWidth(" ") / 1000 * fontSize;
                        String[] lineWords = line.toString().trim().split("\\s+");
                        int numSpaces = lineWords.length - 1;
                        float extraSpace = (endX1 - startX1 - width) / numSpaces;

                        StringBuilder justifiedLine = new StringBuilder();

                        for (int i = 0; i < lineWords.length - 1; i++) {
                            justifiedLine.append(lineWords[i]).append(" ");
                            int extraSpaces = Math.round(extraSpace / spaceWidth);
                            for (int j = 0; j < extraSpaces; j++) {
                                justifiedLine.append(" ");
                            }
                        }

                        justifiedLine.append(lineWords[lineWords.length - 1]);

                        contentStream.showText(justifiedLine.toString());
                        contentStream.newLineAtOffset(0, -leading);
                        line = new StringBuilder(word + " ");
                    }
                }

                contentStream.showText(line.toString());
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText(" ");
                contentStream.newLineAtOffset(0, -leading);
            }

            // START SIGNATURE
            contentStream.showText(" ");
            yCordinate -= fontHeight;  //This line is to track the yCordinate
            contentStream.newLineAtOffset(0, -leading);

            yCordinate1 -= leading;

            contentStream.showText("Yours Faithfully,");
            yCordinate1 -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);

            yCordinate1 -= leading;

            contentStream.showText("For: Crusader Sterling Pensions Limited.");
            yCordinate1 -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);


            contentStream.showText(" ");
            yCordinate -= fontHeight;  //This line is to track the yCordinate
            contentStream.newLineAtOffset(0, -leading);

            yCordinate1 -= leading;

            contentStream.showText(" ");
            yCordinate -= fontHeight;  //This line is to track the yCordinate
            contentStream.newLineAtOffset(0, -leading);

            yCordinate1 -= leading;

            contentStream.showText("Funmilola Oluwajoba");
            yCordinate1 -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);

            yCordinate1 -= leading;

            contentStream.showText("Head, Contributions");
            yCordinate1 -= fontHeight;
            contentStream.newLineAtOffset(0, -leading);

            // END SIGNATURE

            contentStream.endText();
            contentStream.close();

            document.save(outputPath);
            document.close();

            log.info("PDF created successfully at:  outputPath");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
