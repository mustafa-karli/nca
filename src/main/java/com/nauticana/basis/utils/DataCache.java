package com.nauticana.basis.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.IAbstractService;
import com.nauticana.basis.model.CaptionTranslation;
import com.nauticana.basis.model.DomainName;
import com.nauticana.basis.model.DomainValue;
import com.nauticana.basis.model.Language;
import com.nauticana.basis.model.UserMenu;
import com.nauticana.basis.service.AuthorityGroupService;
import com.nauticana.basis.service.AuthorityObjectService;
import com.nauticana.basis.service.CountryService;
import com.nauticana.basis.service.DomainNameService;
import com.nauticana.basis.service.LanguageService;
import com.nauticana.basis.service.MainMenuService;
import com.nauticana.basis.service.ScreenPageService;
import com.nauticana.basis.service.TableControllerStaticService;
import com.nauticana.basis.service.UserAccountService;
import com.nauticana.business.model.ApplicationConfig;
import com.nauticana.business.service.ApplicationConfigService;
import com.nauticana.business.service.BusinessOwnerService;
import com.nauticana.business.service.BusinessPartnerService;
import com.nauticana.business.service.BusinessServiceService;
import com.nauticana.business.service.CustomerService;
import com.nauticana.business.service.PartnerAddressService;
import com.nauticana.business.service.SubcontractorService;
import com.nauticana.business.service.VendorService;
import com.nauticana.finance.service.AccountSchemaService;
import com.nauticana.finance.service.TaxTypeService;
import com.nauticana.maintenance.service.EquipmentService;
import com.nauticana.maintenance.service.MxCounterTypeService;
import com.nauticana.maintenance.service.ServiceTypeService;
import com.nauticana.material.service.ManufacturerService;
import com.nauticana.material.service.MaterialAttributeGroupService;
import com.nauticana.material.service.MaterialGroupService;
import com.nauticana.material.service.MaterialService;
import com.nauticana.material.service.MaterialTypeService;
import com.nauticana.personnel.service.EmployeeService;
import com.nauticana.personnel.service.OrganizationService;
import com.nauticana.personnel.service.PersonService;
import com.nauticana.personnel.service.PositionTypeService;
import com.nauticana.personnel.service.QualificationService;
import com.nauticana.project.service.CategoryService;
import com.nauticana.project.service.ProjectService;
import com.nauticana.project.service.WorkerService;
import com.nauticana.request.service.ProductTypeByDefineService;
import com.nauticana.sales.service.SalesCampaignService;

@Service
public class DataCache {

	private static HashMap<String, Domain>						domains				= null;
	private static HashMap<String, PortalLanguage>				languages			= null;
	private static HashMap<String, NamsSession>					sessions			= null;
	@SuppressWarnings("rawtypes")
	private static HashMap<String, IAbstractService>			lookups				= null;
	private static HashMap<String, HashMap<String, String[]>>	fieldHeaders		= null;
	private static HashMap<String,  ApplicationConfig> applicationConfigs = new HashMap<String, ApplicationConfig>(0);
	private static HashMap<Integer, ApplicationConfig> applicationConfigi = new HashMap<Integer, ApplicationConfig>(0);

	public static final String									defaultLanguage		= "TR";

	public static long											SESSION_LIFE_TIME	= 0;

	@Autowired
	DomainNameService											domainNameService;

	@Autowired
	LanguageService												languageService;

	@Autowired
	MainMenuService												mainMenuService;

	@Autowired
	ScreenPageService											screenPageService;

	@Autowired
	UserAccountService											userAccountService;

	
	@Autowired
	AuthorityGroupService										authorityGroupService;

	@Autowired
	AuthorityObjectService										authorityObjectService;

	@Autowired
	BusinessOwnerService										businessOwnerService;

	@Autowired
	BusinessPartnerService										businessPartnerService;

	@Autowired
	BusinessServiceService										businessServiceService;

	@Autowired
	CountryService												countryService;

	@Autowired
	CustomerService												customerService;

	@Autowired
	VendorService												vendorService;

	@Autowired
	TaxTypeService												taxTypeService;

	@Autowired
	MxCounterTypeService										mxCounterTypeService;

	@Autowired
	EquipmentService											equipmentService;

	@Autowired
	ServiceTypeService											serviceTypeService;

	@Autowired
	CategoryService												categoryService;

	@Autowired
	AccountSchemaService										accountSchemaService;

	@Autowired
	ProjectService												projectService;

	@Autowired
	SubcontractorService										subcontractorService;

	@Autowired
	WorkerService												workerService;

	@Autowired
	ManufacturerService											manufacturerService;

	@Autowired
	MaterialAttributeGroupService								materialAttributeGroupService;

	@Autowired
	MaterialGroupService										materialGroupService;

	@Autowired
	MaterialTypeService											materialTypeService;

	@Autowired
	ProductTypeByDefineService									productTypeByDefineService;

	@Autowired
	MaterialService												materialService;

	@Autowired
	PersonService												personService;

	@Autowired
	EmployeeService												employeeService;

	@Autowired
	OrganizationService											organizationService;

	@Autowired
	QualificationService										qualificationService;

	@Autowired
	PartnerAddressService										partnerAddressService;

	@Autowired
	PositionTypeService											positionTypeService;
	
	@Autowired
	SalesCampaignService										salesCampaignService;

	@Autowired
	TableControllerStaticService								tableControllerStaticService;

	@Autowired
	ApplicationConfigService								applicationConfigService;

	private DataCache( ) {
	}

	@SuppressWarnings("rawtypes")
	public void loadLookupServices() {
		lookups = new HashMap<String, IAbstractService>();
		lookups.put(accountSchemaService.tableName(), accountSchemaService);
		lookups.put(authorityGroupService.tableName(), authorityGroupService);
		lookups.put(authorityObjectService.tableName(), authorityObjectService);
		lookups.put(businessOwnerService.tableName(), businessOwnerService);
		lookups.put(businessPartnerService.tableName(), businessPartnerService);
		lookups.put(businessServiceService.tableName(), businessServiceService);
		lookups.put(categoryService.tableName(), categoryService);
		lookups.put(countryService.tableName(), countryService);
		lookups.put(customerService.tableName(), customerService);
		lookups.put(domainNameService.tableName(), domainNameService);
		lookups.put(employeeService.tableName(), employeeService);
		lookups.put(equipmentService.tableName(), equipmentService);
		lookups.put(languageService.tableName(), languageService);
		lookups.put(mainMenuService.tableName(), mainMenuService);
		lookups.put(manufacturerService.tableName(), manufacturerService);
		lookups.put(materialService.tableName(), materialService);
		lookups.put(materialAttributeGroupService.tableName(), materialAttributeGroupService);
		lookups.put(materialGroupService.tableName(), materialGroupService);
		lookups.put(materialTypeService.tableName(), materialTypeService);
		lookups.put(mxCounterTypeService.tableName(), mxCounterTypeService);
		lookups.put(organizationService.tableName(), organizationService);
		lookups.put(partnerAddressService.tableName(), partnerAddressService);
		lookups.put(personService.tableName(), personService);
		lookups.put(positionTypeService.tableName(), positionTypeService);
		lookups.put(projectService.toString(), projectService);
		lookups.put(qualificationService.tableName(), qualificationService);
		lookups.put(salesCampaignService.tableName(), salesCampaignService);
		lookups.put(screenPageService.tableName(), screenPageService);
		lookups.put(serviceTypeService.tableName(), serviceTypeService);
		lookups.put(subcontractorService.tableName(), subcontractorService);
		lookups.put(tableControllerStaticService.tableName(), tableControllerStaticService);
		lookups.put(taxTypeService.tableName(), taxTypeService);
		lookups.put(userAccountService.tableName(), userAccountService);
		lookups.put(vendorService.tableName(), vendorService);
		lookups.put(workerService.tableName(), workerService);
		lookups.put(productTypeByDefineService.tableName(), productTypeByDefineService);
	}

	public void loadDomainValues(DomainName domainName) {
		Domain domain = domains.get(domainName.getId());
		for (DomainValue dv : domainName.getDomainValues()) {
			domain.addOption(dv.getId().getRefvalue(), dv.getCaption());
		}
	}
	
	public void loadDomains() {
		if (domains == null)
			domains = new HashMap<String, Domain>();
		else
			domains.clear();
		List<DomainName> dl = domainNameService.findAll();
		for(DomainName dn : dl) {
			domains.put(dn.getId(), new Domain(dn.getId(), dn.getCaption(), dn.getKeysize(), dn.getSortBy()));
			loadDomainValues(dn);
		}
	}
	
	public void loadTranslations(Language language) {
		PortalLanguage pl = languages.get(language.getId());
		for (CaptionTranslation t : language.getCaptionTranslations()) {
			String caption = t.getId().getCaption();
			pl.translations.put(caption, t.getLabellower());
			String icon = Icons.getIcon(caption + "_ICON");
			if (icon != null) {
				pl.iconText.put(caption, "<i class=\"" + icon + "\"> " + t.getLabellower() + " </i>");
				pl.icon.put(caption, "<i class=\"" + icon + "\"></i>");
			}
		}
	}
	
	public Map<String, PortalLanguage> loadLanguages() {
		if (languages == null)
			languages = new HashMap<String, PortalLanguage>();
		else
			languages.clear();
		List<Language> languageList = languageService.findAll();
		for (Language language : languageList) {
			try {
				languages.put(language.getId(), new PortalLanguage(language.getId(), language.getCaption(), language.getLocaleStr()));
				loadTranslations(language);
				System.out.println("\n  New language added :\n     " + language.getId() + " " + language.getCaption());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return languages;
	}
	
	public Domain getDomain(String domainName) {
		if (domains == null) loadDomains();
		return domains.get(domainName);
	}
	
	public Map<String, String> getDomainOptions(String domainName) {
		if (domains == null) loadDomains();
		Domain d = domains.get(domainName);
		if (d == null) return null;
		return d.getOptions();
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, String> getLookupOptions(String tableName, int client) {
		if (lookups == null) loadLookupServices();
		IAbstractService service = lookups.get(tableName);
		if (service == null) return null;
		String[][] list = service.findAllForLookup(client);
		if (list == null) return null;
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.length; i++) {
			map.put(list[i][0], list[i][1]);
		}
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	public String getLookupJson(String tableName, int client) {
		if (lookups == null) loadLookupServices();
		IAbstractService service = lookups.get(tableName);
		if (service == null) return null;
		String[][] list = service.findAllForLookup(client);
		if (list == null) return "[]";
		String jsonTxt = "";
		for (int i = 0; i < list.length; i++) {
			jsonTxt = jsonTxt +  ",{\"key\":\"" + list[i][0] + "\",\"txt\":\"" + list[i][1] + "\"}";
		}
		if (Utils.emptyStr(jsonTxt)) return "[]";
		jsonTxt = "[" + jsonTxt.substring(1) + "]"; 
		return jsonTxt;
	}
	
	public Map<String, String> getDomainOptions(String domainName, PortalLanguage language) {
		if (domains == null) loadDomains();
		Domain d = domains.get(domainName);
		if (d == null) return null;
		return d.getOptions(language);
	}
	
	public PortalLanguage getLanguage(String language) {
		if (languages == null) {
//			System.out.println("\n  Languages null, loading:\n");
			loadLanguages();
		}
		if (Utils.emptyStr(language))
			return languages.get(defaultLanguage);
		else
			return languages.get(language);
		
	}

	public Map<String, PortalLanguage> getLanguages() {
		if (languages == null) {
//			System.out.println("\n  Languages null, loading:\n");
			loadLanguages();
		}
		return languages;
	}
	
	public NamsSession getSession(String key) {
		if (sessions == null) {
			sessions = new HashMap<String, NamsSession>();
			return null;
		}
		NamsSession ses = sessions.get(key);
		if (ses == null) return null;
		if (ses.checkTime() > SESSION_LIFE_TIME) {
			sessions.remove(key);
			return null;
		}
		return ses;
	}
	
	public NamsSession putSession(String key, String user, int personId, int client, String lang, List<UserMenu> menu) {
		if (sessions == null) {
			sessions = new HashMap<String, NamsSession>();
		}
		NamsSession ses = sessions.get(key);
		if (ses != null) {
			sessions.remove(key);
		}
		ses = new NamsSession(user, personId, client, lang, menu);
		sessions.put(ses.getKey(), ses);
		return ses;
	}
	
	public String[] getFieldHeaders(String tableName, String langcode) {
		if (fieldHeaders == null)
			fieldHeaders = new HashMap<String, HashMap<String, String[]>>();
		HashMap<String, String[]> headers = fieldHeaders.get(tableName);
		if (headers == null) return null;
		return headers.get(langcode);
	}
	
	public void putFieldHeaders(String tableName, HashMap<String, String[]> headers) {
		if (fieldHeaders == null)
			fieldHeaders = new HashMap<String, HashMap<String, String[]>>();
		HashMap<String, String[]> oldHeaders = fieldHeaders.get(tableName);
		if (oldHeaders != null) {
			fieldHeaders.remove(tableName);
		}
		fieldHeaders.put(tableName, headers);
	}

	public ApplicationConfig getApplicationConfig(int ownerId) {
		ApplicationConfig applicationConfig = applicationConfigi.get(ownerId);
		if (applicationConfig != null) return applicationConfig;
		
		applicationConfig = applicationConfigService.findById(ownerId);
		applicationConfigi.put(ownerId, applicationConfig);
		applicationConfigs.put(applicationConfig.getDomain(), applicationConfig);
		return applicationConfig;
	}
	
	public ApplicationConfig getApplicationConfig(String domain) {
		ApplicationConfig applicationConfig = applicationConfigs.get(domain);
		if (applicationConfig != null) return applicationConfig;

		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> filters = new ArrayList<String>();
		ArrayList<Integer> types = new ArrayList<Integer>();
		fields.add("DOMAIN");
		filters.add(domain);
		types.add(FieldType.T_STR);
		try {
			List<ApplicationConfig> list = applicationConfigService.search(fields, filters, types);
			applicationConfig = list.get(0);
			applicationConfigi.put(applicationConfig.getId(), applicationConfig);
			applicationConfigs.put(applicationConfig.getDomain(), applicationConfig);
			return applicationConfig;
		} catch (Exception e) {
			return null;
		}
	}
	
}
