package com.ihs.springhibernate.controller.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping("/test")
public class DetailTestController {
	@Autowired
	private IUserSession userSession;

	String testId = "";

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
	public ModelAndView printTest(HttpServletRequest request) throws FileNotFoundException {
		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(resources.getJSP_HOME());

		modelAndView.getModel().put("resources", resources);
		modelAndView.getModel().put("currentUser", userSession);

		String fileName = request.getParameter("name");
		Integer printTestId = Integer.parseInt(testId);

		String printType = request.getParameter("type");
		Integer printTypeId = Integer.parseInt(printType);

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
			try {

				Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
						Font.BOLD);
				Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
						Font.BOLD);
				Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
						Font.BOLD);

				PdfPTable table = new PdfPTable(3);
				table.setSpacingBefore(300);
				PdfPCell fake = new PdfPCell();
				fake.setBorder(Rectangle.NO_BORDER);

				PdfPCell dataCell = new PdfPCell();
				dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				dataCell.setBorder(Rectangle.NO_BORDER);

				Paragraph title = new Paragraph("Recruitment Test", catFont);
				title.setAlignment(Element.ALIGN_CENTER);

				//

				Paragraph subTitle = new Paragraph(
						"Interactive Health Solutions", subFont);
				subTitle.setAlignment(Element.ALIGN_CENTER);

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

				table.setWidths(columnWidths);

				String workingDirectory = System.getProperty("user.home");
				String absoluteFilePath = workingDirectory + File.separator
						+ "Downloads" + File.separator + fileName + ".pdf";

				PdfWriter.getInstance(document, new FileOutputStream(
						absoluteFilePath));
				document.open();

				document.add(title);
				document.add(subTitle);
				document.add(table);
				document.newPage();

				int i = 1;
				char c = 65;

				for (Question question : finalQuestionList) {
					String garbageData = question.getDescription();
					String finalData = garbageData.replaceAll("[\\<p>\\</p>]",
							"");

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
					choiceList.clear();
					c = 65;
					i++;
				}

			} catch (DocumentException e) {
				System.err.println(e.getMessage());
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
			document.close();

		}

		else if (printTypeId == 2) {
			Document document1 = new Document();
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

				Paragraph testName = new Paragraph("Test Title: " + test.getName(), subFont);

				String workingDirectory = System.getProperty("user.home");
				String absoluteFilePath = workingDirectory + File.separator
						+ "Downloads" + File.separator + test.getName()
						+ "Answers.pdf";
				
				PdfWriter.getInstance(document1, new FileOutputStream(
						absoluteFilePath));
				document1.open();

				document1.add(answerTitle);
				document1.add(answerSubTitle);
				document1.add(new Phrase("\n"));
				document1.add(testName);
				document1.add(new Phrase("\n"));
				document1.add(new Phrase("\n"));

				for (Question question : finalQuestionList) {
					Paragraph questionTitle = new Paragraph(i + ". ");
					document1.add(questionTitle);
					List<QuestionData> choiceList = question
							.getQuestionDataList();
					for (QuestionData data : choiceList) {

						if (!data.isCorrect()) {
							c++;
						}

						else {
							Paragraph choiceParagraph = new Paragraph(c);
							document1.add(choiceParagraph);
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

		}

		return modelAndView;
	}
}
