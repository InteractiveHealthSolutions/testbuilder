package com.ihs.springhibernate.controller.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.TabExpander;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionData;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.model.TestQuestion;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.LocatorEx.Snapshot;

@Controller
@RequestMapping("/test")
public class DetailTestController {
	@Autowired
	private IUserSession userSession;

	String testId = "";
	boolean a = true;

	@RequestMapping(value = "/viewtest", method = RequestMethod.GET)
	public ModelAndView getQuestion(
			@RequestParam(value = "status", required = false) String status,
			HttpServletRequest request) {

		testId = request.getParameter("id");
		Test test = TestDAO.getTest(TestDAO.By.ID, testId,
				TestDAO.FetchType.EAGER);

		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getRole().getId() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) //
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST()
						+ "/" + resources.getJSP_VIEW_TEST());

				modelAndView.getModelMap().put("detailTest", test);
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				modelAndView.getModel().put("status", status);
			} else {
				modelAndView = new ModelAndView("redirect:/"
						+ resources.getJSP_INDEX());
				// modelAndView.getModel().put("status", status);
			}
		}

		else {
			modelAndView = new ModelAndView("redirect:/"
					+ resources.getJSP_INDEX());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/viewtest", method = RequestMethod.POST)
	public void printTest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ResourcesName resources = new ResourcesName();

		if (a == false || a == true) {

			ModelAndView modelAndView = new ModelAndView(
					resources.getJSP_HOME());

			modelAndView.getModel().put("resources", resources);
			modelAndView.getModel().put("currentUser", userSession);

			// String fileName = request.getParameter("name");
			Integer printTestId = Integer.parseInt(testId);

			String printType1 = request.getParameter("type");
			char printType2 = printType1.charAt(printType1.length() - 1);
			String printType = String.valueOf(printType2);
			Integer printTypeId = Integer.parseInt(printType);

			if (a == false) {
				String splitter[] = printType.split("");
				printTypeId = Integer.parseInt(splitter[1]);
			}

			List<TestQuestion> questionList = new ArrayList<TestQuestion>();
			questionList = QuestionDAO.getQuestionForTest(printTestId);

			List<Question> allQuestionList = QuestionDAO
					.getAllQuestion(QuestionDAO.FetchType.EAGER);

			List<Question> finalQuestionList = new ArrayList<Question>();

			for (int i = 0; i < questionList.size(); i++) {
				for (int j = 0; j < allQuestionList.size(); j++) {
					if (questionList.get(i).getQuestion_id()
							.equals(allQuestionList.get(j).getId())
							&& allQuestionList.get(j).getQuestionType().getId() == 3) {
						finalQuestionList.add(allQuestionList.get(j));
						break;
					}
				}
			}

			if (printTypeId == 1) {

				Document document = new Document();
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				try {

					Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
							Font.BOLD);
					
					Font catFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 18,
							Font.UNDERLINE | Font.BOLD);
					
					Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
							Font.BOLD);
					Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11,
							Font.BOLD);
					Font smallWithoutBold = new Font(Font.FontFamily.TIMES_ROMAN, 11,
							Font.NORMAL);
					
					Font verySmall = new Font(Font.FontFamily.TIMES_ROMAN, 6,
							Font.NORMAL);
					
					Font mediumSmall = new Font(Font.FontFamily.TIMES_ROMAN, 9,
							Font.NORMAL);
					
					
		/*			PdfPTable table = new PdfPTable(3);
					table.setSpacingBefore(300);
					PdfPCell fake = new PdfPCell();
					fake.setBorder(Rectangle.NO_BORDER);

					PdfPCell dataCell = new PdfPCell();
					dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					dataCell.setBorder(Rectangle.NO_BORDER);

				

					float[] columnWidths = { 30f, 50f, 20f };

					dataCell.addElement(new Paragraph(
							"Name:            ___________________", smallBold));
					dataCell.addElement(new Phrase("\n"));
					dataCell.addElement(new Paragraph(
							"Email:            ___________________", smallBold));
					dataCell.addElement(new Phrase("\n"));
					dataCell.addElement(new Paragraph(
							"Phone No:     ___________________", smallBold));
					dataCell.addElement(new Phrase("\n"));
					dataCell.addElement(new Paragraph(
							"Date:              ___________________", smallBold));

					table.addCell(fake);
					table.addCell(dataCell);
					table.addCell(fake);

					table.setWidths(columnWidths);*/
					
					
					
					
					Test test = TestDAO.getTest(TestDAO.By.ID, testId,
							TestDAO.FetchType.EAGER);

					response.setHeader("Expires", "0");
					response.setHeader("Cache-Control",
							"must-revalidate, post-check=0, pre-check=0");
					response.setHeader("Pragma", "public");
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + test.getName() + ".pdf");

					PdfWriter.getInstance(document, byteArrayOutputStream);
					document.open();

					Paragraph title = new Paragraph("Recruitment Test", catFont);
					title.setAlignment(Element.ALIGN_CENTER);

					//

					Paragraph subTitle = new Paragraph(
							"Interactive Health Solutions", subFont);
					subTitle.setAlignment(Element.ALIGN_CENTER);
					
					Paragraph applicantTitle = new Paragraph("APPLICANT INFORMATION FORM",catFont1);
					applicantTitle.setAlignment(Element.ALIGN_CENTER);
					
					Paragraph positionLine = new Paragraph("POSITION APPLIED FOR:__________________________________________ DATE:___________________",smallWithoutBold);
					Paragraph personalInformation = new Paragraph("PERSONAL INFORMATION", smallBold);
					
					Paragraph blockLetters = new Paragraph("(PLEASE USE BLOCK LETTERS)", verySmall);
					
					
					PdfPTable table = new PdfPTable(2);
					PdfPCell cell1 = new PdfPCell(new Paragraph("  Name",smallWithoutBold));
					cell1.setFixedHeight(25);
			        PdfPCell cell2 = new PdfPCell(new Paragraph(""));
			        PdfPCell cell3 = new PdfPCell(new Paragraph("  Email Address",smallWithoutBold));
			        cell3.setFixedHeight(25);
			        PdfPCell cell4 = new PdfPCell(new Paragraph(""));
			        PdfPCell cell5 = new PdfPCell(new Paragraph("  Date Of Birth",smallWithoutBold));
			        cell5.setFixedHeight(25);
			        PdfPCell cell6 = new PdfPCell(new Paragraph(""));
			        PdfPCell cell7 = new PdfPCell(new Paragraph("  Address",smallWithoutBold));
			        cell7.setFixedHeight(25);
			        PdfPCell cell8 = new PdfPCell(new Paragraph(""));
			        PdfPCell cell9 = new PdfPCell(new Paragraph("  Phone Number",smallWithoutBold));
			        cell9.setFixedHeight(40);
			        PdfPCell cell10 = new PdfPCell(new Paragraph("  Land Line: ____________________ \n\n  Cell Phone:____________________", mediumSmall));
			        
			        float[] columnWidths = {40f, 140f};
			        
			        
			        table.addCell(cell1);
			        table.addCell(cell2);
			        table.addCell(cell3);
			        table.addCell(cell4);
			        table.addCell(cell5);
			        table.addCell(cell6);
			        table.addCell(cell7);
			        table.addCell(cell8);
			        table.addCell(cell9);
			        table.addCell(cell10);
			    
			        table.setWidths(columnWidths);
			        table.setSpacingBefore(10);
			        table.setHorizontalAlignment(Element.ALIGN_LEFT);
			        
			        
			        Paragraph academicInfo = new Paragraph("ACADEMIC INFORMATION", smallBold);
			        Paragraph block1 = new Paragraph("(PLEASE ENTER MOST RECENT DEGREE / DIPLOMA FIRST)", verySmall);
			        
			    	PdfPTable table1 = new PdfPTable(6);
			    	float[] columnWidths1 = {18f, 40f, 50f, 20f, 33f, 20f };
			    	table1.setWidths(columnWidths1);
			    	table1.setSpacingBefore(10);
			    	table1.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	
			    	PdfPCell cell11 = new PdfPCell(new Paragraph("  S.No", smallWithoutBold));
			    	cell1.setFixedHeight(30);
			    	PdfPCell cell12 = new PdfPCell(new Paragraph("  Name Of Institute", smallWithoutBold));
			    	PdfPCell cell13 = new PdfPCell(new Paragraph("  Major Subjects", smallWithoutBold));
			    	PdfPCell cell14 = new PdfPCell(new Paragraph("  GPA or\n  Grade ", smallWithoutBold));
			    	PdfPCell cell15 = new PdfPCell(new Paragraph("  Degree or\n  Diploma", smallWithoutBold));
			    	PdfPCell cell16 = new PdfPCell(new Paragraph("  Year of\n  Passing", smallWithoutBold));
			    	PdfPCell cell17 = new PdfPCell(new Paragraph(""));
			    	cell17.setFixedHeight(55);
			    	PdfPCell cell18 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell19 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell20 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell21 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell22 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell23 = new PdfPCell(new Paragraph(""));
			    	cell23.setFixedHeight(55);
			    	PdfPCell cell24 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell25 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell26 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell27 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell28 = new PdfPCell(new Paragraph(""));
		
			    	
			    	table1.addCell(cell11);
			    	table1.addCell(cell12);
			    	table1.addCell(cell13);
			    	table1.addCell(cell14);
			    	table1.addCell(cell15);
			    	table1.addCell(cell16);
			    	table1.addCell(cell17);
			    	table1.addCell(cell18);
			    	table1.addCell(cell19);
			    	table1.addCell(cell20);
			    	table1.addCell(cell21);
			    	table1.addCell(cell22);
			    	table1.addCell(cell23);
			    	table1.addCell(cell24);
			    	table1.addCell(cell25);
			    	table1.addCell(cell26);
			    	table1.addCell(cell27);
			    	table1.addCell(cell28);
			    	
			    	Paragraph EmploymentInfo = new Paragraph("EMPLOYMENT INFORMATION", smallBold);
			        Paragraph block2 = new Paragraph("(PLEASE ENTER MOST RECENT JOB / INTERNSHIP FIRST)", verySmall);
			        
			        PdfPTable table2 = new PdfPTable(5);
			    	float[] columnWidths2 = {18f, 50f, 35f, 35f, 40f };
			    	table2.setWidths(columnWidths2);
			    	table2.setSpacingBefore(10);
			    	table2.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	
			    	PdfPCell cell31 = new PdfPCell(new Paragraph("  S.No", smallWithoutBold));
			    	cell31.setFixedHeight(30);
			    	PdfPCell cell32 = new PdfPCell(new Paragraph("  Organization Name", smallWithoutBold));
			    	PdfPCell cell33 = new PdfPCell(new Paragraph("  Designation", smallWithoutBold));
			    	PdfPCell cell34 = new PdfPCell(new Paragraph("  From - To ", smallWithoutBold));
			    	PdfPCell cell35 = new PdfPCell(new Paragraph("  Reason for\n  Leaving", smallWithoutBold));
			    	
			    	PdfPCell cell36 = new PdfPCell(new Paragraph(""));
			    	cell36.setFixedHeight(25);
			    	PdfPCell cell37 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell38 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell39 = new PdfPCell(new Paragraph(""));
			    	PdfPCell cell40 = new PdfPCell(new Paragraph(""));
			    	cell40.setFixedHeight(25);
			    	
			    	table2.addCell(cell31);
			    	table2.addCell(cell32);
			    	table2.addCell(cell33);
			    	table2.addCell(cell34);
			    	table2.addCell(cell35);
			    	table2.addCell(cell36);
			    	table2.addCell(cell37);
			    	table2.addCell(cell38);
			    	table2.addCell(cell39);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	table2.addCell(cell40);
			    	
			    	  
			        PdfPTable table3 = new PdfPTable(2);
			    	table3.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	
			    	PdfPCell cell41 = new PdfPCell(new Paragraph("  TECHNICAL SKILLS / EXPERTISE", smallBold));
			    	cell41.setFixedHeight(25);
			    	PdfPCell cell42 = new PdfPCell(new Paragraph("  FIELD OF INTEREST", smallBold));
			    	PdfPCell cell43 = new PdfPCell(new Paragraph(""));
			    	cell43.setFixedHeight(70);
			    	
			    	
			    	table3.addCell(cell41);
			    	table3.addCell(cell42);
			    	table3.addCell(cell43);
			    	table3.addCell(cell43);
			    	
			    	Paragraph lastLine = new Paragraph("Expected Time of Joining: ______________________                      Signature: ______________",smallWithoutBold);
					
		
					document.setMargins(50, 15, 15, 20);
					document.newPage();
					document.add(applicantTitle);
					document.add(new Phrase("\n"));
					document.add(positionLine);
					document.add(new Phrase(Chunk.NEWLINE));
					document.add(personalInformation);
					document.add(blockLetters);
					document.add(table);
					document.add(new Phrase(Chunk.NEWLINE));
					document.add(academicInfo);
					document.add(block1);
					document.add(table1);
					document.add(new Phrase(Chunk.NEWLINE));
					document.add(EmploymentInfo);
					document.add(block2);
					document.add(table2);
					document.add(table3);
					document.add(new Phrase(Chunk.NEWLINE));
					document.add(new Phrase(Chunk.NEWLINE));
					document.add(lastLine);
					
					document.newPage();
					document.add(title);
					document.add(subTitle);
					document.add(new Phrase("\n"));
				

					int i = 1;
					char c = 65;

					for (Question question : finalQuestionList) {
						String garbageData = question.getDescription();
						String finalData = garbageData
								.replaceAll("<[^>]*>", "");
						Paragraph questionTitle = new Paragraph(i + ". "
								+ finalData);
						document.add(questionTitle);
						document.add(new Phrase("\n"));

						List<QuestionData> choiceList = question
								.getQuestionDataList();
						for (QuestionData data : choiceList) {
							Paragraph choiceParagraph = new Paragraph(c + ". "
									+ data.getData());
							document.add(choiceParagraph);
							c++;
						}

						document.add(new Phrase("\n"));
						document.add(new Phrase("\n"));
						choiceList.clear();
						c = 65;
						i++;
					}

				} catch (DocumentException e) {
					System.err.println(e.getMessage());
				}
				document.close();

				a = false;
				OutputStream os = response.getOutputStream();
				byteArrayOutputStream.writeTo(os);
				os.flush();
				os.close();
			}

			else if (printTypeId == 2) {
				Document document1 = new Document();
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				try {

					Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
							Font.BOLD);
					Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
							Font.BOLD);
					Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
							Font.BOLD);

					Test test = TestDAO.getTest(TestDAO.By.ID, testId,
							TestDAO.FetchType.EAGER);
					int i = 1;
					char c = 65;
					Paragraph answerTitle = new Paragraph(
							"Interactive Health Solutions", catFont);
					answerTitle.setAlignment(Element.ALIGN_CENTER);
					Paragraph answerSubTitle = new Paragraph("Answer Sheet",
							subFont);
					answerSubTitle.setAlignment(Element.ALIGN_CENTER);

					Paragraph testName = new Paragraph("Test Title: "
							+ test.getName(), subFont);
					testName.setAlignment(Element.ALIGN_CENTER);

					/*
					 * String workingDirectory =
					 * System.getProperty("user.home"); String absoluteFilePath
					 * = workingDirectory + File.separator + "Downloads" +
					 * File.separator + test.getName() + "Answers.pdf";
					 */

					response.setHeader("Expires", "0");
					response.setHeader("Cache-Control",
							"must-revalidate, post-check=0, pre-check=0");
					response.setHeader("Pragma", "public");
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + test.getName()
									+ "Answers.pdf");

					PdfWriter.getInstance(document1, byteArrayOutputStream);
					document1.open();

					document1.add(answerTitle);
					document1.add(answerSubTitle);
					document1.add(new Phrase("\n"));
					document1.add(testName);
					document1.add(new Phrase("\n"));
					document1.add(new Phrase("\n"));

					for (Question question : finalQuestionList) {
						List<QuestionData> choiceList = question
								.getQuestionDataList();
						for (QuestionData data : choiceList) {

							if (!data.isCorrect()) {
								c++;
							}

							else {
								Paragraph questionTitle = new Paragraph(i
										+ ". " + c);
								document1.add(questionTitle);
							}

						}

						document1.add(new Phrase("\n"));
						choiceList.clear();
						c = 65;
						i++;
					}
				} catch (DocumentException e) {
					System.err.println(e.getMessage());
				}
				document1.close();
				a = false;
				OutputStream os = response.getOutputStream();
				byteArrayOutputStream.writeTo(os);
				os.flush();
				os.close();
			}
		}
	}
}
