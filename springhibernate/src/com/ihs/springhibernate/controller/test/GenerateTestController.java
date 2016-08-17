package com.ihs.springhibernate.controller.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.SchemeCategoryDAO;
import com.ihs.springhibernate.dao.SchemeDAO;
import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionData;
import com.ihs.springhibernate.model.Scheme;
import com.ihs.springhibernate.model.SchemeCategory;
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
public class GenerateTestController {
	@Autowired
	private IUserSession userSession;
	String secondPara;
	Integer schemeId;
	int finalTotalQues = 0;
	int returnTotalQues = 0;

	@RequestMapping(value = "/generatetest", method = RequestMethod.GET)
	public ModelAndView getSchemeData(@ModelAttribute("newTest") Test newTest,
			HttpServletRequest request) {
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();
		List<List<Question>> questionCollection = new ArrayList<List<Question>>();
		int totalQuestions = 0;
		int categoryId = 0;
		double calculateVal = 0;
		int weightage = 0;
		int questionAmount = 0;
		finalTotalQues = 0;
		returnTotalQues = 0;

		String id = request.getParameter("id");

		secondPara = request.getParameter("type");
		schemeId = Integer.parseInt(id);

		if (userSession.getName() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				modelAndView = new ModelAndView(resources.getFOLDER_TEST()
						+ "/" + resources.getJSP_FINALIZE_TEST());

				// Test newTest = new Test();
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				// modelAndView.getModelMap().put("newTest", newTest);
				modelAndView.getModelMap().put("schemeId", schemeId);
				// modelAndView.getModelMap().put("questionList", questionList);

				List<Scheme> schemeList = new ArrayList<Scheme>();
				schemeList = SchemeDAO.getSchemeById(schemeId);

				List<Scheme> schemeListAll = SchemeDAO.getAllSchemes();
			

				List<SchemeCategory> schemeCategoryList = new ArrayList<SchemeCategory>();
				schemeCategoryList = SchemeCategoryDAO
						.getSchemeCategory(schemeId);

				totalQuestions = schemeList.get(0).getTotalQuestions();

				HashMap<Integer, Integer> categoryWeightageList = new HashMap<Integer, Integer>();

				modelAndView.getModelMap().put("schemeList", schemeList);
			
				

				for (SchemeCategory sc : schemeCategoryList) {
					categoryId = sc.getCategory_id();
					weightage = sc.getWeightage();
					calculateVal = weightage / 100.0;
					BigDecimal b = BigDecimal.valueOf(calculateVal
							* totalQuestions);
					int scale = 0;
					BigDecimal b1 = b.setScale(scale, RoundingMode.HALF_UP);
					questionAmount = Integer.parseInt(b1.toString());
					categoryWeightageList.put(categoryId, questionAmount);
					finalTotalQues += questionAmount;
				}
				
				
				
				questionCollection = QuestionDAO
						.getQuestionForTest(categoryWeightageList);
				
				for(List<Question> innerList : questionCollection){
					returnTotalQues += innerList.size();
				}
				
				if(returnTotalQues >= finalTotalQues){
					modelAndView.getModelMap().put("questionCollection",
							questionCollection);
					modelAndView.getModel().put("schemeListAll", schemeListAll);
					modelAndView.getModelMap().put("schemeCategoryList",
							schemeCategoryList);
				}
				
				else {
					modelAndView.getModelMap().put("status",
							"Not enough questions according to scheme");
				}
			}

			else {
				modelAndView = new ModelAndView("redirect:/"
						+ resources.getJSP_INDEX());
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/generatetest", method = RequestMethod.POST)
	ModelAndView submitTest(@ModelAttribute("newTest") @Valid Test newTest,
			BindingResult result, HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException {

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		List<List<Question>> questionCollection = new ArrayList<List<Question>>();
		int totalQuestions = 0;
		int categoryId = 0;
		double calculateVal = 0;
		int weightage = 0;
		int questionAmount = 0;

		ResourcesName resources = new ResourcesName();

		String btnClick = request.getParameter("type");

		ModelAndView modelAndView = new ModelAndView();
		// new ModelAndView("redirect:/" + resources.getJSP_HOME());

		modelAndView.getModel().put("resources", resources);

		modelAndView.getModel().put("newTest", newTest);

		modelAndView.getModel().put("currentUser", userSession);

		if (userSession.getName() != null) {

			boolean isThere = false;
			List<Test> testList = TestDAO.getAllTest(TestDAO.FetchType.EAGER);

			for (Test t : testList) {
				if (newTest.getName().toLowerCase()
						.equals(t.getName().toLowerCase())) {
					isThere = true;
				}
			}
			
			if(!btnClick.equals("save") && !(btnClick.substring(0, 5).equals("print"))) {

				if (!(secondPara == null)) {
					modelAndView = new ModelAndView("redirect:/"
							+ resources.getFOLDER_TEST() + "/"
							+ resources.getJSP_SELECT_SCHEME());
				}

				else {
					modelAndView = new ModelAndView("redirect:/"
							+ resources.getFOLDER_TEST() + "/"
							+ resources.getJSP_CREATE_SCHEME());
				}

			}
			
			else if(returnTotalQues != finalTotalQues){
				modelAndView.getModelMap().put("status",
						"Not enough questions according to scheme");
			}

			else if (StringUtils.isBlank(newTest.getName())
					|| StringUtils.isBlank(newTest.getDescription())) {

				modelAndView.getModelMap().put("status",
						"Required fields missing");

				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				// modelAndView.getModelMap().put("newTest", newTest);
				modelAndView.getModelMap().put("schemeId", schemeId);
				// modelAndView.getModelMap().put("questionList", questionList);

				List<Scheme> schemeList = new ArrayList<Scheme>();
				schemeList = SchemeDAO.getSchemeById(schemeId);

				List<Scheme> schemeListAll = SchemeDAO.getAllSchemes();
				modelAndView.getModel().put("schemeListAll", schemeListAll);

				List<SchemeCategory> schemeCategoryList = new ArrayList<SchemeCategory>();
				schemeCategoryList = SchemeCategoryDAO
						.getSchemeCategory(schemeId);

				totalQuestions = schemeList.get(0).getTotalQuestions();

				HashMap<Integer, Integer> categoryWeightageList = new HashMap<Integer, Integer>();

				modelAndView.getModelMap().put("schemeList", schemeList);
				modelAndView.getModelMap().put("schemeCategoryList",
						schemeCategoryList);

				for (SchemeCategory sc : schemeCategoryList) {
					categoryId = sc.getCategory_id();
					weightage = sc.getWeightage();
					calculateVal = weightage / 100.0;
					BigDecimal b = BigDecimal.valueOf(calculateVal
							* totalQuestions);
					int scale = 0;
					BigDecimal b1 = b.setScale(scale, RoundingMode.HALF_UP);
					questionAmount = Integer.parseInt(b1.toString());
					categoryWeightageList.put(categoryId, questionAmount);
				}

				questionCollection = QuestionDAO
						.getQuestionForTest(categoryWeightageList);
				modelAndView.getModelMap().put("questionCollection",
						questionCollection);

			}

			else if (isThere == true) {
				modelAndView.getModelMap().put("status",
						"Test name already exists");

				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				// modelAndView.getModelMap().put("newTest", newTest);
				modelAndView.getModelMap().put("schemeId", schemeId);
				// modelAndView.getModelMap().put("questionList", questionList);

				List<Scheme> schemeList = new ArrayList<Scheme>();
				schemeList = SchemeDAO.getSchemeById(schemeId);

				List<Scheme> schemeListAll = SchemeDAO.getAllSchemes();
				modelAndView.getModel().put("schemeListAll", schemeListAll);

				List<SchemeCategory> schemeCategoryList = new ArrayList<SchemeCategory>();
				schemeCategoryList = SchemeCategoryDAO
						.getSchemeCategory(schemeId);

				totalQuestions = schemeList.get(0).getTotalQuestions();

				HashMap<Integer, Integer> categoryWeightageList = new HashMap<Integer, Integer>();

				modelAndView.getModelMap().put("schemeList", schemeList);
				modelAndView.getModelMap().put("schemeCategoryList",
						schemeCategoryList);

				for (SchemeCategory sc : schemeCategoryList) {
					categoryId = sc.getCategory_id();
					weightage = sc.getWeightage();
					calculateVal = weightage / 100.0;
					BigDecimal b = BigDecimal.valueOf(calculateVal
							* totalQuestions);
					int scale = 0;
					BigDecimal b1 = b.setScale(scale, RoundingMode.HALF_UP);
					questionAmount = Integer.parseInt(b1.toString());
					categoryWeightageList.put(categoryId, questionAmount);
				}

				questionCollection = QuestionDAO
						.getQuestionForTest(categoryWeightageList);
				modelAndView.getModelMap().put("questionCollection",
						questionCollection);

			}

			else {
				Integer newlySavedId = -1;
				newlySavedId = TestDAO.save(newTest);

				if (btnClick.equals("save")) {

					if (newlySavedId == -1) {
						modelAndView.getModelMap().put("status",
								resources.getMESSAGE_FAIL_ADD());
					}

					else {
						modelAndView.getModelMap().put("status",
								resources.getMESSAGE_ADD());
					}
				}

				else if (btnClick.substring(0, 5).equals("print")) {

					//String[] splitter = btnClick.split("/");
				//	String fileName = splitter[1];

					List<TestQuestion> questionList = new ArrayList<TestQuestion>();
					questionList = QuestionDAO.getQuestionForTest(newlySavedId);

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

						Font catFont = new Font(Font.FontFamily.TIMES_ROMAN,
								18, Font.BOLD);
						Font subFont = new Font(Font.FontFamily.TIMES_ROMAN,
								16, Font.BOLD);
						Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN,
								12, Font.BOLD);

						PdfPTable table = new PdfPTable(3);
						table.setSpacingBefore(300);
						PdfPCell fake = new PdfPCell();
						fake.setBorder(Rectangle.NO_BORDER);

						PdfPCell dataCell = new PdfPCell();
						dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
						dataCell.setBorder(Rectangle.NO_BORDER);

						Paragraph title = new Paragraph("Recruitment Test",
								catFont);
						title.setAlignment(Element.ALIGN_CENTER);

						Paragraph subTitle = new Paragraph(
								"Interactive Health Solutions", subFont);
						subTitle.setAlignment(Element.ALIGN_CENTER);

						float[] columnWidths = { 30f, 50f, 20f };

						dataCell.addElement(new Paragraph(
								"Name:            ___________________",
								smallBold));
						dataCell.addElement(new Phrase("\n"));
						dataCell.addElement(new Paragraph(
								"Email:            ___________________",
								smallBold));
						dataCell.addElement(new Phrase("\n"));
						dataCell.addElement(new Paragraph(
								"Phone No:     ___________________", smallBold));
						dataCell.addElement(new Phrase("\n"));
						dataCell.addElement(new Paragraph(
								"Date:              ___________________",
								smallBold));

						table.addCell(fake);
						table.addCell(dataCell);
						table.addCell(fake);

						table.setWidths(columnWidths);

						//String userName = System.getProperty("user.home");
						// String userSplit[] = userName.split(File.separator);
						// String userSplit[] = userName.split("\\\\");

					//	String file = "";

						/*
						 * for(String s : userSplit ) { file = file + s +
						 * File.separator; }
						 */

				/*	file = userName.replace("\\", File.separator
								+ File.separator)
								+ File.separator
								+ "Downloads"
								+ File.separator
								+ fileName + ".pdf";*/

						
						//  String file = "c:/" + fileName + ".pdf";
						 

						/* PdfWriter.getInstance(document, new
						 FileOutputStream(file));*/
							
						response.setContentType("application/pdf"); 
						response.setHeader("Content-Disposition", "attachment; filename=" + newTest.getName() + ".pdf"); 
						
						PdfWriter.getInstance(document,byteArrayOutputStream);
						//PdfWriter.getInstance(document, new FileOutputStream(
							//	fileName + ".pdf"));
						document.open();

						document.add(title);
						document.add(subTitle);
						document.add(table);
						document.newPage();

						int i = 1;
						char c = 65;

						for (Question question : finalQuestionList) {
							String garbageData = question.getDescription();
							String finalData = garbageData.replaceAll("<[^>]*>", "");

							Paragraph questionTitle = new Paragraph(i + ". "
									+ finalData);
							document.add(questionTitle);
							document.add(new Phrase("\n"));

							List<QuestionData> choiceList = question
									.getQuestionDataList();
							for (QuestionData data : choiceList) {
								Paragraph choiceParagraph = new Paragraph(c
										+ ". " + data.getData());
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
					}
					document.close();
					
					

					/*byte[] pdfBytes = byteArrayOutputStream.toByteArray();
					ZipOutputStream zip = new ZipOutputStream(response.getOutputStream());
					zip.write(pdfBytes);
					zip.closeEntry();
					zip.close(); */
					
					OutputStream os = response.getOutputStream();
		            byteArrayOutputStream.writeTo(os);
		            os.flush();
		            os.close();

				}

			}
		}
		return modelAndView;

	}

}
