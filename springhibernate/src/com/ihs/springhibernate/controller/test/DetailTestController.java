package com.ihs.springhibernate.controller.test;

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
public class DetailTestController
{
	@Autowired
	private IUserSession userSession;
	
	String testId = "";

	@RequestMapping(value = "/viewtest", method = RequestMethod.GET)
	public ModelAndView getQuestion(@RequestParam(value = "status", required = false) String status, HttpServletRequest request)
	{
		
		testId = request.getParameter("id");
		Test test = TestDAO.getTest(TestDAO.By.ID, testId, TestDAO.FetchType.EAGER);

		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getRole().getId() != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) //
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_VIEW_TEST());

				modelAndView.getModelMap().put("detailTest", test);
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				modelAndView.getModel().put("status", status);
			}
			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
				// modelAndView.getModel().put("status", status);
			}
		}

		else
		{
			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		}

		return modelAndView;
	}
	
	@RequestMapping(value="/viewtest", method= RequestMethod.POST)
	public ModelAndView printTest(HttpServletRequest request){
		ResourcesName resources = new ResourcesName();
		
		ModelAndView modelAndView = new ModelAndView(resources.getJSP_HOME());
		
		modelAndView.getModel().put("resources", resources);
		modelAndView.getModel().put("currentUser", userSession);
		
		String fileName = request.getParameter("name");
		Integer printTestId = Integer.parseInt(testId);
		
		List<TestQuestion> questionList = new ArrayList<TestQuestion>();
		questionList = QuestionDAO.getQuestionForTest(printTestId);

		List<Question> allQuestionList = QuestionDAO
				.getAllQuestion(QuestionDAO.FetchType.EAGER);

		List<Question> finalQuestionList = new ArrayList<Question>();

		for (int i = 0; i < questionList.size(); i++) {
			for (int j = 0; j < allQuestionList.size(); j++) {
				if (questionList.get(i).getQuestion_id()
						.equals(allQuestionList.get(j).getId())
						&& allQuestionList.get(j).getQuestionType()
								.getId() == 3) {
					finalQuestionList.add(allQuestionList.get(j));
					break;
				}
			}
		}

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

			String userName = System.getProperty("user.home");
			String userSplit[] = userName.split("\\\\");
			String file = userSplit[0] + "/" + userSplit[1] + "/"
					+ userSplit[2] + "/Downloads/" + fileName + ".pdf";

			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			document.add(title);
			document.add(subTitle);
			document.add(table);
			document.newPage();

			int i = 1;
			char c = 65;

			for (Question question : finalQuestionList) {
				String garbageData = question.getDescription();
				String finalData = garbageData.replaceAll(
						"[\\<p>\\</p>]", "");

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
		
		
		
		return modelAndView;
	}
}
