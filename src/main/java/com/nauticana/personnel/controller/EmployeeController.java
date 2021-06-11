package com.nauticana.personnel.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.UserAccount;
import com.nauticana.basis.service.UserAccountService;
import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.personnel.model.Employee;
import com.nauticana.personnel.model.EmployeeId;
import com.nauticana.personnel.model.Person;
import com.nauticana.personnel.model.PositionType;
import com.nauticana.personnel.model.PositionTypeId;
import com.nauticana.personnel.model.UserAccountOwner;
import com.nauticana.personnel.model.UserAccountOwnerId;
import com.nauticana.personnel.model.ViewEmployeeUser;
import com.nauticana.personnel.service.PersonService;
import com.nauticana.personnel.service.PersonnelJdbcService;
import com.nauticana.personnel.service.PositionTypeService;
import com.nauticana.personnel.service.UserAccountOwnerService;

@Controller
@ResponseBody
@RequestMapping("/" + Employee.ROOTMAPPING)
public class EmployeeController extends AbstractController<Employee, EmployeeId> {
	
    @Autowired
    protected PersonnelJdbcService		personnelJdbcService;

    @Autowired
    protected PersonService				personService;

    @Autowired
    protected UserAccountService		userAccountService;

    @Autowired
    protected UserAccountOwnerService	userAccountOwnerService;

    @Autowired
    protected PositionTypeService		positionTypeService;

    @RequestMapping(value = "/empUser", method = RequestMethod.GET)
    public ModelAndView empUserList(HttpServletRequest request) throws IOException {

        // Check for user and read authorization on table
    	HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());

        int partnerId = Integer.parseInt(request.getParameter("id"));
        List<ViewEmployeeUser> records = personnelJdbcService.viewEmployeeUsers(partnerId);
        ModelAndView model = new ModelAndView("personnel/empUserList");

        model.addObject("partnerId", partnerId);
        model.addObject("records", records);
        model.addObject("language", language);
        model.addObject("genders", dataCache.getDomainOptions("GENDER", language));
        model.addObject("countries", dataCache.getLookupOptions("COUNTRY", -1));
//        model.addObject("roles", 
        model.addObject(Labels.POSTLINK, "employee/empUser");
        model.addObject(Labels.PAGETITLE, language.getText("EMPLOYEE_USERS"));
        return model;
    }

    @RequestMapping(value = "/empUser", method = RequestMethod.POST)
    public ModelAndView fastEntryPost(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        String uname = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(uname))
            return new ModelAndView("redirect:/userAccount/login");

        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(uname, Labels.TABLE, Labels.INSERT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());
        String nextpage = request.getParameter(Labels.NEXTPAGE);

        int partnerId = Integer.parseInt(request.getParameter("partnerId"));
		String position = request.getParameter("position");
		String emailAddress = request.getParameter("emailAddress");
		String workPhone = request.getParameter("workPhone");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		Date birthday = Labels.dmyDF.parse(request.getParameter("birthday"));
		String gender = request.getParameter("gender");
		String nationality = request.getParameter("nationality");
		String governmentId = request.getParameter("governmentId");
		String personelMail = request.getParameter("personelMail");
		String cellPhone = request.getParameter("cellPhone");
		String username = request.getParameter("username").toUpperCase(Locale.ENGLISH);
		String userMail = request.getParameter("userMail");

        ViewEmployeeUser veu = new ViewEmployeeUser(0, Utils.onlyDate(), position, emailAddress, workPhone, firstName, middleName, lastName, birthday, gender, nationality, governmentId, personelMail, cellPhone, username, "I", userMail, null, null);

        try {
            addPerson(veu, partnerId);
		} catch (Exception e) {
			ModelAndView res = empUserList(request);
			res.addObject("errorText", e.getMessage());
			res.addObject("veu", veu);
			return res;
		}
		
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:/employee/empUser?id="+partnerId);
		else
			return new ModelAndView("redirect:/" + nextpage);
    }
        
    public void addPerson(ViewEmployeeUser veu, int partnerId) throws Exception {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> filters = new ArrayList<String>();
		ArrayList<Integer> types = new ArrayList<Integer>();
		
		fields.add("NATIONALITY");
		filters.add(veu.getNationality());
		types.add(FieldType.T_STR);
		
		fields.add("GOVERNMENT_ID");
		filters.add(veu.getGovernmentId());
		types.add(FieldType.T_STR);
		
		List<Person> personnel = personService.search(fields, filters, types);
		
		fields.clear();
		filters.clear();
		types.clear();
		
		UserAccount userAccount = userAccountService.findById(veu.getUsername());
		
		Person person = null;
		boolean owned = false;
		if (!personnel.isEmpty()) {
			for (Person p : personnel)
				if (p.getBirthday().getTime() == veu.getBirthday().getTime() && p.getLastName().equals(veu.getLastName()))
					person = p;
			if (person == null) {
				throw new Exception("Another person exists with same government ID " + veu.getGovernmentId() + " in country " + veu.getNationality());
			}
		}

		if (userAccount != null) {
			if (person == null) {
				throw new Exception("User account " + veu.getUsername() + " in use!");
			}
			for (UserAccountOwner o : userAccount.getUserAccountOwners())
				if (o.getPerson().equals(person))
					owned = true;
				else {
					throw new Exception("User account belongs to someone else : " + o.getPerson().getFirstName() + " " + o.getPerson().getLastName());
				}
		}
		
		if (person == null) {
			person = new Person();
			person.setBirthday(veu.getBirthday());
			person.setCellPhone(veu.getCellPhone());
			person.setFirstName(veu.getFirstName());
			person.setGender(veu.getGender());
			person.setGovernmentId(veu.getGovernmentId());
			person.setLastName(veu.getLastName());
			person.setMiddleName(veu.getMiddleName());
			person.setNationality(veu.getNationality());
			person.setPersonalMail(veu.getPersonelMail());
			person = personService.create(person);
		}
		
		if (userAccount == null) {
			userAccount = new UserAccount();
			userAccount.setEmailAddress(veu.getUserMail());
			userAccount.setFirstName(veu.getFirstName());
			userAccount.setId(veu.getUsername());
			userAccount.setLastName(veu.getLastName());
			userAccount.setStatus("I");
			userAccount = userAccountService.create(userAccount);
			
			// TODO
			
		}
		
		if (!owned) {
			UserAccountOwner o = new UserAccountOwner();
			UserAccountOwnerId id = new UserAccountOwnerId();
			id.setPersonId(person.getId());
			id.setUsername(userAccount.getId());
			id.setBegda(new Date());
			o.setId(id);
			o.setPerson(person);
			o.setUserAccount(userAccount);
			o = userAccountOwnerService.create(o);
		}
		
		PositionTypeId ptId = new PositionTypeId(partnerId, veu.getPosition());
		PositionType positionType = positionTypeService.findById(ptId);
		if (positionType == null) {
			positionType = new PositionType();
			positionType.setId(ptId);
			positionType.setCaption(veu.getPosition());
		}
		
		Employee emp = new Employee();
		EmployeeId empId = new EmployeeId(person.getId(), Utils.onlyDate());
		emp.setId(empId);
		emp.setCellPhone(veu.getCellPhone());
		emp.setEmailAddress(veu.getEmailAddress());
		emp.setOwnerId(partnerId);
		emp.setPerson(person);
		emp.setPosition(veu.getPosition());
		emp.setWorkPhone(veu.getWorkPhone());
		emp = modelService.create(emp);

		basisService.addUserRole(userAccount.getId(), "HELPDESK_USER", new Date(), Utils.maxDate());
		
    }
}

