package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
import java.util.Map;

import Exception.CustomClassNotFoundException;
import Exception.CustomConnectException;
import Exception.CustomIllegalAccessException;
import Exception.CustomNotBoundException;
import Exception.CustomSQLException;
import Interface.CarAccident;
import Interface.CarAccidentList;
import Interface.CompensationClaim;
import Interface.CompensationClaimList;
import Interface.Contract;
import Interface.ContractList;
import Interface.Counsel;
import Interface.CounselApplication;
import Interface.CounselApplicationList;
import Interface.CounselList;
import Interface.Customer;
import Interface.Customer.EGender;
import Interface.CustomerList;
import Interface.FamilyHistory;
import Interface.FamilyHistoryList;
import Interface.Guarantee;
import Interface.GuaranteeList;
import Interface.Insurance;
import Interface.InsuranceApplication;
import Interface.InsuranceApplicationList;
import Interface.InsuranceList;
import Interface.Payment;
import Interface.PaymentList;
import Interface.Survey;
import Interface.SurveyList;
import Interface.Terms;
import Interface.TermsList;

public class Main {

	public static void main(String[] args) throws Exception, CustomClassNotFoundException, CustomConnectException,
			CustomIllegalAccessException, CustomNotBoundException, CustomSQLException {

		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1300);
			CarAccidentList carAccidentList = (CarAccidentList) registry.lookup("CarAccidentList");

			Registry registry2 = LocateRegistry.getRegistry("localhost", 1301);
			SurveyList surveyList = (SurveyList) registry2.lookup("SurveyList");
			// surveyList 객체 사용

			// CompensationClaimList 객체 찾아오기
			Registry registry3 = LocateRegistry.getRegistry("localhost", 1302);
			CompensationClaimList compensationClaimList = (CompensationClaimList) registry3
					.lookup("CompensationClaimList");

			// contract 서버 두 개
			Registry registry4 = LocateRegistry.getRegistry("localhost", 1303);
			PaymentList paymentListImpl = (PaymentList) registry4.lookup("PaymentList");

			Registry registry5 = LocateRegistry.getRegistry("localhost", 1304);
			ContractList contractListImpl = (ContractList) registry5.lookup("ContractList");

			// surveyList 객체 사용

			// CompensationClaimList 객체 찾아오기
			Registry registry6 = LocateRegistry.getRegistry("localhost", 1305);
			CustomerList customerListImpl = (CustomerList) registry6.lookup("CustomerList");

			Registry registry7 = LocateRegistry.getRegistry("localhost", 1306);
			FamilyHistoryList familyHistoryListImpl = (FamilyHistoryList) registry7.lookup("FamilyHistoryList");

			Registry registry8 = LocateRegistry.getRegistry("localhost", 1307);
			CounselList counselListImpl = (CounselList) registry8.lookup("CounselList");

			// surveyList 객체 사용

			// CompensationClaimList 객체 찾아오기
			Registry registry9 = LocateRegistry.getRegistry("localhost", 1308);
			CounselApplicationList counselApplicationListImpl = (CounselApplicationList) registry9
					.lookup("CounselApplicationList");

			// CompensationClaimList 객체 찾아오기
			Registry registry10 = LocateRegistry.getRegistry("localhost", 1309);
			TermsList termsListImpl = (TermsList) registry10.lookup("TermsList");

			Registry registry11 = LocateRegistry.getRegistry("localhost", 1310);
			GuaranteeList guaranteeList = (GuaranteeList) registry11.lookup("GuaranteeList");

			Registry registry12 = LocateRegistry.getRegistry("localhost", 1311);
			InsuranceList insuranceList = (InsuranceList) registry12.lookup("InsuranceList");

			// surveyList 객체 사용

			// CompensationClaimList 객체 찾아오기
			Registry registry13 = LocateRegistry.getRegistry("localhost", 1312);
			InsuranceApplicationList insuranceApplicationList = (InsuranceApplicationList) registry13
					.lookup("InsuranceApplicationList");

			String userChoice = "";
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

			while (!userChoice.equals("x")) {
				showMenu();
				userChoice = inputReader.readLine().trim();
				switch (userChoice) {
				case "1":
					createCompensationClaim(insuranceList, compensationClaimList, carAccidentList, contractListImpl,
							customerListImpl, inputReader);
					break;
				case "2":
					showOnSaleInsurance(insuranceList, insuranceApplicationList, customerListImpl,
							familyHistoryListImpl, guaranteeList, termsListImpl, inputReader, "Customer");
					break;
				case "3":
					showCouncel(inputReader, counselApplicationListImpl, counselListImpl, customerListImpl);
					break;
				case "4":
					showSubscriptionInsurance(inputReader, contractListImpl, customerListImpl, insuranceList,
							compensationClaimList, paymentListImpl);
					break;
				case "5":
					// 보함료 조회/납부
					showInsurancePayment(inputReader, customerListImpl, contractListImpl, paymentListImpl,
							insuranceList);
					break;
				case "6":
					showCustomerList(customerListImpl, inputReader, familyHistoryListImpl, contractListImpl,
							insuranceList, paymentListImpl, compensationClaimList);
					break;
				case "7":
					retrieveCompensationClaim(insuranceList, compensationClaimList, carAccidentList, surveyList,
							inputReader);
					break;
				case "8":
					designInsurance(insuranceList, termsListImpl, customerListImpl, familyHistoryListImpl,
							guaranteeList, inputReader, insuranceApplicationList);
					break;
				case "9":
					showManageConsultation(inputReader, counselListImpl, customerListImpl, counselApplicationListImpl);
					break;
				case "10":
					showInsuranceApplicationList(contractListImpl, insuranceApplicationList, insuranceList,
							customerListImpl, familyHistoryListImpl, inputReader);
					break;
				case "11":
					// 추후 수정 - 계약유지대상자 조회 하면에서 '만기 계약자 조회 메뉴' 클릭 시 이동하는 곳
					showPaymentManagement(inputReader, customerListImpl, contractListImpl, paymentListImpl,
							insuranceList);
					break;
				case "x":
					break;
				default:
					System.out.println("잘못된 선택지입니다.");

				}
			}
		} catch (RemoteException e) {
			System.out.println("서버와의 원격 통신 중 오류가 발생했습니다: " + e.getMessage());
			System.out.println("잠시 후에 다시 시도해 주세요.");
		} catch (CustomNotBoundException e) {
			System.out.println("요청한 서비스를 찾을 수 없습니다: " + e.getMessage());
			System.out.println("관리자에게 문의하여 문제를 해결해 주세요.");
		} catch (MalformedURLException e) {
			System.out.println("잘못된 URL 형식으로 연결을 시도했습니다: " + e.getMessage());
			System.out.println("URL 주소를 다시 확인하고 재시도해 주세요.");
		} catch (CustomConnectException e) {
			System.out.println("서버에 연결할 수 없습니다: " + e.getMessage());
			System.out.println("인터넷 연결을 확인하고, 서버가 정상적으로 실행 중인지 확인해 주세요.");
		} catch (IllegalAccessException e) {
			System.out.println("잘못된 접근으로 인해 오류가 발생했습니다: " + e.getMessage());
			System.out.println("권한이 필요한 작업에 접근하지 않도록 주의해 주세요.");
		} catch (CustomClassNotFoundException | NoClassDefFoundError e) {
			System.out.println("필요한 클래스를 찾을 수 없습니다: " + e.getMessage());
			System.out.println("프로그램이 올바르게 설치되었는지 확인하고, 필요한 파일이 제대로 위치해 있는지 확인해 주세요.");
		} catch (IOException e) {
			// IOException 예외 처리
			throw new CustomConnectException("입출력 오류가 발생했습니다", e);
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다: " + e.getMessage());
			System.out.println("문제가 지속되면 관리자에게 문의하여 도움을 받으세요.");
		}

	}

	private static void showMenu() {
		System.out.println("\n****************** 보험사 초기 화면 *******************");
		System.out.println("==========고객용=========");
		System.out.println("1. 보험금 청구");
		System.out.println("2. 보험 조회");
		System.out.println("3. 고객 상담 신청");
		System.out.println("4. 내 보험 확인");
		System.out.println("5. 보험료 조회/납부");
		System.out.println("==========직원용=========");
		System.out.println("6. 고객 조회");
		System.out.println("7. 보험금 청구 내역");
		System.out.println("8. 보험 설계");
		System.out.println("9. 상담 정보 관리");
		System.out.println("10. 보험 가입 신청 내역");
		System.out.println("11. 납입 관리 메뉴");
		System.out.println("x. Exit");
	}

	private static void showInsuranceApplicationList(ContractList contractList,
			InsuranceApplicationList insuranceApplicationList, InsuranceList insuranceList,
			CustomerList customerListImpl, FamilyHistoryList familyHistoryList, BufferedReader inputReader)
			throws Exception {
		String inputAID;
		System.out.println("****************** 보험 가입 신청 내역 *******************");
		System.out.println("신청ID 보험ID 고객ID 신청일자");
		for (InsuranceApplication insuranceApplication : insuranceApplicationList.retrieve()) {
			System.out.println("신청ID: " + insuranceApplication.getApplicationID() + "  보험ID: "
					+ insuranceApplication.getInsuranceID() + "  고객ID: " + insuranceApplication.getCustomerID()
					+ "  신청일자: " + insuranceApplication.getCreatedAt());
		}
		System.out.println("1. 상세보기");
		System.out.println("2. 청약서 조회");
		System.out.println("3. 보험료 산정");
		String userChoice = "";
		userChoice = inputReader.readLine().trim();
		InsuranceApplication insuranceApplication;
		Insurance insurance;
		switch (userChoice) {
		case "1":
			System.out.println("상세내용을 조회할 신청건의 신청ID를 입력하세요");
			insuranceApplication = insuranceApplicationList.getApplicationbyId(inputReader.readLine().trim());
			insurance = insuranceList.getInsurancebyId(insuranceApplication.getInsuranceID());
			Customer customer = customerListImpl.getCustomerByID(insuranceApplication.getCustomerID());
			System.out.println("보험종류: " + insurance.getType() + "  보험명: " + insurance.getInsuranceName() + "  신청일자:"
					+ insuranceApplication.getCreatedAt() + "\n이름: " + customer.getCustomerName() + "  생년월일: "
					+ customer.getBirth() + "  성별: " + customer.getEGender() + "  주소: " + customer.getAddress()
					+ "  전화번호: " + customer.getPnumber() + "  직업: " + customer.getJob() + "\n가족력");
			for (FamilyHistory familyHistory : familyHistoryList.getFamilyHistoryByCID(customer.getCustomerID())) {
				System.out.println(
						"가족관계: " + familyHistory.getRelationship() + "  질환명: " + familyHistory.getDiseaseName());
			}
			System.out.println("보험기간: " + insuranceApplication.getInsurancePeriod() + "  납입주기: "
					+ insuranceApplication.getPaymentCycle());
			break;
		case "2":
			System.out.println("청약서를 조회할 신청건의 신청ID를 입력하세요");
			inputAID = inputReader.readLine().trim();
			if (insuranceApplicationList.getApplicationbyId(inputAID) == null)
				System.out.println("존재하지 않는 ID입니다");
			else
				System.out.println(insuranceApplicationList.getApplicationbyId(inputAID).getSubscriptionFilePath());

			break;
		case "3":
			System.out.println("보험료를 산정할 신청건의 신청ID를 입력하세요");
			inputAID = inputReader.readLine().trim();
			if (insuranceApplicationList.getApplicationbyId(inputAID) == null)
				System.out.println("존재하지 않는 ID입니다");
			else {
				insuranceApplication = insuranceApplicationList.getApplicationbyId(inputAID);
				insurance = insuranceList.getInsurancebyId(insuranceApplication.getInsuranceID());
				Customer customer1 = customerListImpl.getCustomerByID(insuranceApplication.getCustomerID());
				ratePremium(insuranceApplicationList, contractList, insuranceApplication, insurance, customer1,
						familyHistoryList, inputReader);
			}
		}
	}

	private static void ratePremium(InsuranceApplicationList insuranceApplicationList, ContractList contractList,
			InsuranceApplication insuranceApplication, Insurance insurance, Customer customer,
			FamilyHistoryList familyHistoryList, BufferedReader inputReader) throws Exception {
		System.out.println(insurance.getType() + " " + insurance.getInsuranceName() + " 기본보험료:"
				+ insurance.getBasicPremium() + " 요율:" + insurance.getRate());
		System.out.println(customer.getCustomerName() + " " + customer.getBirth() + " " + customer.getEGender() + " "
				+ customer.getAddress() + " " + customer.getPnumber() + " " + customer.getJob());
		for (FamilyHistory familyHistory : familyHistoryList.getFamilyHistoryByCID(customer.getCustomerID())) {
			System.out.println("가족관계: " + familyHistory.getRelationship() + "  질환명: " + familyHistory.getDiseaseName());
		}
		System.out.println("********보험료 산정**********");
		System.out.print("보험료 산정 이유: ");
		insuranceApplication.setReasonOfApproval(inputReader.readLine().trim());
		System.out.print("최대보장한도: ");
		insuranceApplication.setMaxCompensation(Integer.parseInt(inputReader.readLine().trim()));
		System.out.print("산정보험료: ");
		insuranceApplication.setPremium(Integer.parseInt(inputReader.readLine().trim()));
		System.out.println("요율산정이유:" + insuranceApplication.getReasonOfApproval() + "\n위 내용에 따라, 고객의 보험료가 "
				+ insuranceApplication.getPremium() + "원으로 산정되었습니다.");
		System.out.println("최대보장한도는 " + insuranceApplication.getMaxCompensation() + "원입니다.");
		approveInsuranceApplication(insuranceApplicationList, contractList, insuranceApplication, insurance, customer,
				inputReader);
	}

	private static void approveInsuranceApplication(InsuranceApplicationList insuranceApplicationList,
			ContractList contractList, InsuranceApplication insuranceApplication, Insurance insurance,
			Customer customer, BufferedReader inputReader) throws Exception {
		System.out.println(
				insuranceApplication.getApplicationID() + " " + insurance.getType() + " " + insurance.getInsuranceName()
						+ " " + customer.getCustomerName() + " " + insuranceApplication.getReasonOfApproval() + " "
						+ insuranceApplication.getMaxCompensation() + " " + insuranceApplication.getPremium());
		System.out.println("1. 승인\n2. 거절");
		if (inputReader.readLine().trim().equals("1")) {
			insuranceApplication.setApproval(true);
			Contract contract = new Contract();
			contract.setCustomerID(customer.getCustomerID());
			contract.setInsuranceID(insurance.getInsuranceID());
			contract.setInsurancePeriod(insurance.getPeriodOfInsurance());
			contract.setPremium(insuranceApplication.getPremium());
			contract.setPaymentCycle(insuranceApplication.getPaymentCycle());
			contract.setMaxCompensation(insuranceApplication.getMaxCompensation());
			contract.setDateOfSubscription(insuranceApplication.getCreatedAt());
			contract.setDateOfMaturity(LocalDate.parse("2099-12-31"));
			contract.setMaturity(false);
			contract.setResurrection(false);
			contract.setCancellation(false);
			contractList.add(contract);
			System.out.println(insuranceApplication.getApplicationID() + " 보험 가입이 승인되었습니다.");
		} else {
			System.out.println("거절 사유: ");
			insuranceApplication.setReasonOfApproval(inputReader.readLine().trim());
			System.out.println(insuranceApplication.getReasonOfApproval() + "\n위 사유로 인해 "
					+ insuranceApplication.getApplicationID() + " 보험 가입이 거절되었습니다.");
		}
		insuranceApplicationList.updateInsuranceApplication(insuranceApplication);
	}

	private static void showManageConsultation(BufferedReader inputReader, CounselList counselList,
			CustomerList customerList, CounselApplicationList counselApplicationList) throws Exception {
		if (selectConsultationCase(inputReader)) { // 상담 정보 조회
			String id = null;
			Counsel counsel = null;
			CounselApplication counselApplication = null;
			Customer customer = null;
			do {
				System.out.print("고객 ID : ");
				id = inputReader.readLine().trim();
			} while (!checkInputId(id));
			customer = customerList.getCustomerByID(id);
			counselApplication = counselApplicationList.getCounselApplicationByCustomerId(customer.getCustomerID());
			counsel = counselList.getCounselbyCounselId(counselApplication.getCounselID());
			if (counsel == null) {
				System.out.println("일치하는 data가 하나도 없습니다.");
				return;
			}
			showCounselSchedule(counselApplication, customer, counsel);
			if (!getCustomerDetails(inputReader))
				return;
			if (counsel.getContent() == null) {
				System.out.println("상담을 아직 진행하지 않아 내용이 없습니다.");
				return;
			}
			showDetailcounselInfo(counselApplication, customer, counsel);

			if (selectRetOrDel(inputReader)) // 내용 조회
				showContentInfo(customer, counsel);
			else { // 내용 삭제
				counselList.delete(counsel.getCounselID());
				System.out.println("삭제되었습니다");
			}
		} else { // 상담 정보 등록
			if (selectScheduleOrContent(inputReader)) {
				System.out.println("\n[상담 일정 등록]");
				System.out.println("****** 상담 대기 예비 고객 리스트 ******");
				printAllCounselIDs(counselList);
				Counsel newCounsel = registerCounselDate(inputReader, counselList, counselApplicationList);
				counselList.update(newCounsel);
				System.out.println("상담 일정을 등록하였습니다.");
			} else {
				System.out.println("\n상담 내용 등록");
				String input = null;
				boolean isInputed = false;
				do {
					input = inputCounselId(inputReader);
					isInputed = input.length() != 0;
					if (!isInputed)
						System.out.println("조건을 최소 하나라도 기입했는지 체크해주세요.");
				} while (!isInputed);
				Counsel selectedCounsel = counselList.getCounselbyCounselId(input);
				Customer customer = customerList.retrieveCustomer(selectedCounsel.getCustomerID());
				showCousel(customer, selectedCounsel);
				if (!selectContentAcquire(inputReader))
					return;
				String content = inputContent(inputReader); // 상담내용
				selectedCounsel.setContent(content);
				counselList.update(selectedCounsel);
				System.out.println("상담 내용을 등록하였습니다.");
			}
		}
	}

	private static void showCousel(Customer customer, Counsel selectedCouncel) {
		System.out.println("\n------------------- 상담 일정 목록 -------------------");
		System.out.println("날짜     : " + selectedCouncel.getDateOfCounsel());
		System.out.println("고객명    : " + customer.getCustomerName());
		System.out.println("담당자명  : " + selectedCouncel.getManagerName());
		System.out.println("----------------------------------------------------\n");
	}

	private static void printAllCounselIDs(CounselList counselList) throws RemoteException {
		System.out.println("----------------------------");
		System.out.println("번호\t\t상담 ID");
		System.out.println("----------------------------");

		int i = 1;
		for (Counsel counsel : counselList.retrieve()) {
			System.out.println(i + "\t\t" + counsel.getCounselID());
			i++;
		}

		System.out.println("----------------------------");
	}

	private static Counsel selectFromCouncels(BufferedReader inputReader, List<Counsel> selectedCouncels)
			throws IOException {
		System.out.println("내용을 작성할 상담을 선택해주세요");
		System.out.print("상담 ID : ");
		String id = inputReader.readLine().trim();
		for (Counsel counsel : selectedCouncels) {
			if (counsel.getCounselID().equals(id))
				return counsel;
		}
		return null;
	}

	private static void showContentInfo(Customer customer, Counsel counsel) {
		System.out.println("===============================");
		System.out.println("         상담 내용 정보         ");
		System.out.println("===============================");
		System.out.printf("%-10s %-15s%n", "고객명", customer.getCustomerName());
		System.out.printf("%-10s %-15s%n", "담당자명", counsel.getManagerName());
		System.out.printf("%-10s %-15s%n", "상담 내용", counsel.getContent());
	}

	private static void showDetailcounselInfo(CounselApplication counselApplication, Customer customer,
			Counsel counsel) {
		System.out.println("===============================");
		System.out.println("          세부 내역          ");
		System.out.println("===============================");
		System.out.printf("%-10s %-15s%n", "고객 아이디", customer.getCustomerID());
		System.out.printf("%-10s %-15s%n", "고객명", customer.getCustomerName());
		System.out.printf("%-10s %-15s%n", "고객 전화번호", customer.getPnumber());
		System.out.printf("%-10s %-15s%n", "요구사항", counselApplication.getRequirement());
		System.out.printf("%-10s %-15s%n", "유형", counselApplication.getCategory());
		System.out.printf("%-10s %-15s%n", "내용", counsel.getContent());
	}

	private static Counsel registerCounselDate(BufferedReader inputReader, CounselList counselList,
			CounselApplicationList counselApplicationList) throws ParseException, IOException {
		String counselId = null;
		String content = null;
		String manager = null;
		boolean allInput = false;
		LocalDate date = null;
		Counsel counsel = null;
		CounselApplication counselApplication = null;

		do {
			System.out.println("===============================");
			System.out.println("        상담 일정 등록         ");
			System.out.println("===============================");
			System.out.print("상담 ID: ");
			counselId = inputReader.readLine().trim();

			counsel = counselList.getCounselbyCounselId(counselId);
			if (counsel == null) {
				System.out.println("해당 상담 ID를 가진 상담이 없습니다. 다시 입력해주세요.");
				continue;
			}

			counselApplication = counselApplicationList.getCounselApplicationById(counselId);

			System.out.println("1지망: " + counselApplication.getDateOfFirst());
			System.out.println("2지망: " + counselApplication.getDateOfSecond());

			String input = null;
			boolean validInputDate = false;

			do {
				System.out.print("날짜 입력(1: 1지망, 2: 2지망): ");
				input = inputReader.readLine().trim();
				validInputDate = isValidInput(input);
				if (!validInputDate) {
					System.out.println("1과 2 중에 입력해주세요.");
				}
			} while (!validInputDate);

			date = input.equals("1") ? counselApplication.getDateOfFirst() : counselApplication.getDateOfSecond();

			System.out.print("담당자명: ");
			manager = inputReader.readLine().trim();

			allInput = isAllInput(counselId, manager);
			if (!allInput) {
				System.out.println("항목을 모두 입력해주세요.");
			}
		} while (!allInput);

		counsel.setManagerName(manager);
		counsel.setDateOfCounsel(date);

		return counsel;
	}

	private static boolean isValidInput(String input) {
		return input.equals("1") || input.equals("2");
	}

	private static boolean isAllInput(String counselId, String manager) {
		if (counselId.length() == 0)
			return false;
		if (manager.length() == 0)
			return false;
		return true;
	}

	private static String inputContent(BufferedReader inputReader) throws IOException {
		System.out.println("내용을 입력해 주세요");
		System.out.print("내용 : ");
		return inputReader.readLine().trim();
	}

	private static boolean selectContentAcquire(BufferedReader inputReader) throws IOException {
		System.out.println("내용을 등록하겠습니까?");
		System.out.print("1.예 2. 아니오");
		System.out.print("입력 : ");
		return inputReader.readLine().trim().equals("1");
	}

	private static boolean selectScheduleOrContent(BufferedReader inputReader) throws IOException {
		System.out.println("===============================");
		System.out.println("        상담 정보 등록        ");
		System.out.println("===============================");
		System.out.println("1. 상담 일정 등록");
		System.out.println("2. 상담 내용 등록");
		System.out.print("입력: ");
		return inputReader.readLine().trim().equals("1");
	}

	private static boolean selectRetOrDel(BufferedReader inputReader) throws IOException {
		System.out.print("1.상담 내용 조회. 2.상담 일정 삭제  ");
		return inputReader.readLine().trim().equals("1");
	}

	private static void showCounseList(Customer customer, List<Counsel> selectedCouncels) {
		System.out.println("<상담 일정 목록>");
		for (Counsel counsel : selectedCouncels) {
			System.out.println("날짜 : " + counsel.getDateOfCounsel());
			System.out.println("고객명 : " + customer.getCustomerName());
			System.out.println("담당자명 : " + counsel.getManagerName());
		}
	}

	private static void showCounselSchedule(CounselApplication counselApplication, Customer customer, Counsel counsel) {
		System.out.println("===============================");
		System.out.println("          상담 희망 일정           ");
		System.out.println("===============================");
		System.out.printf("%-10s %-15s%n", "첫째날", counselApplication.getDateOfFirst());
		System.out.printf("%-10s %-15s%n", "둘째날", counselApplication.getDateOfSecond());
		System.out.println();
		System.out.println("===============================");
		System.out.println("          상담 실제 일정           ");
		System.out.println("===============================");
		System.out.printf("%-10s %-15s%n", "실제 상담일", counsel.getDateOfCounsel());
		System.out.println();
		System.out.println("===============================");
		System.out.println("          상담 관련 정보           ");
		System.out.println("===============================");
		System.out.printf("%-10s %-15s%n", "이름", customer.getCustomerName());
		System.out.printf("%-10s %-15s%n", "담당자명", counsel.getManagerName());
	}

	private static boolean checkInputId(String id) {
		if (id.equals("")) {
			System.out.println("조건을 기입 주세요");
			return false;
		}
		for (char c : id.toCharArray()) {
			if (!Character.isDigit(c)) {
				System.out.println("조건을 기입 주세요");
				return false;
			}
		}
		return true;
	}

	private static boolean selectConsultationCase(BufferedReader inputReader) throws IOException {
		System.out.println("===============================");
		System.out.println("       상담 정보 관리        ");
		System.out.println("===============================");
		System.out.println("1. 상담 정보 조회");
		System.out.println("2. 상담 정보 등록");
		System.out.print("입력: ");
		return inputReader.readLine().trim().equals("1");
	}

	private static void showCouncel(BufferedReader inputReader, CounselApplicationList counselApplicationList,
			CounselList counselList, CustomerList customerListImpl) throws Exception {
		getNewCouncel(counselApplicationList, inputReader, counselList, customerListImpl); // 상담 내역 입력
		System.out.println("상담 신청이 완료되었습니다.\n신청하신 상담일자에 전화드릴 예정입니다.");
	}

	private static void getNewCouncel(CounselApplicationList counselApplicationList, BufferedReader inputReader,
			CounselList counselList, CustomerList customerListImpl) throws Exception {
		System.out.println("===============================");
		System.out.println("         상담 신청         ");
		System.out.println("===============================");
		// category, counselID,customerID, dateOfFirst,dateOfSecond,requirement
		CounselApplication counselApplication = new CounselApplication();
		String customerId = null;
		String dateStr1 = null;
		String dateStr2 = null;
		String category = null;
		String requirement = null;
//		do {
//			System.out.print("1. 고객 ID : ");
//			customerId = inputReader.readLine().trim();
//			if (customerListImpl.getCustomerByID(customerId) == null) 
//				System.out.println("존재하지 않는 ID입니다"); 
//			if (customerId.length() == 0)
//				System.out.println("입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
//		} while (customerId.length() == 0);
//		do {
//		    System.out.print("1. 고객 ID: ");
//		    customerId = inputReader.readLine().trim();
//		    
//		    if (customerListImpl.getCustomerByID(customerId) == null) {
//		        System.out.println("존재하지 않는 ID입니다.");
//		    }
//		    
//		    if (customerId.length() == 0) {
//		        System.out.println("입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
//		    }
//		} while (customerId.length() == 0);
		do {
			System.out.print("1. 고객 ID: ");
			customerId = inputReader.readLine().trim();

			if (customerListImpl.getCustomerByID(customerId) == null) {
				System.out.println("존재하지 않는 ID입니다.");
			}

			if (customerId.length() == 0) {
				System.out.println("입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
			}
		} while (customerId.length() == 0 || customerListImpl.getCustomerByID(customerId) == null);
		do {
			System.out.println("== 날짜는 yyyymmdd형태로 입력해주세요 ==");
			System.out.print("2. 1지망 일시 :");
			dateStr1 = inputReader.readLine().trim();
			if (dateStr1.length() == 0)
				System.out.println("입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
		} while (dateStr1.length() == 0);

		int year = Integer.parseInt(dateStr1.substring(0, 4));
		int month = Integer.parseInt(dateStr1.substring(4, 6));
		int day = Integer.parseInt(dateStr1.substring(6, 8));
		LocalDate date1 = LocalDate.of(year, month, day);

		do {
			System.out.print("3. 2지망 일시 : ");
			dateStr2 = inputReader.readLine().trim();
			if (dateStr2.length() == 0)
				System.out.println("입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
		} while (dateStr2.length() == 0);

		year = Integer.parseInt(dateStr2.substring(0, 4));
		month = Integer.parseInt(dateStr2.substring(4, 6));
		day = Integer.parseInt(dateStr2.substring(6, 8));
		LocalDate date2 = LocalDate.of(year, month, day);

		do {
			System.out.print("4. 상담 유형 : ");
			category = inputReader.readLine().trim();
			if (category.length() == 0)
				System.out.println("입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
		} while (category.length() == 0);

		do {
			System.out.print("5. 상세 내용 : ");
			requirement = inputReader.readLine().trim();
			if (requirement.length() == 0)
				System.out.println("입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
		} while (requirement.length() == 0);
		counselApplication.setCustomerID(customerId);
		counselApplication.setDateOfFirst(date1);
		counselApplication.setDateOfSecond(date2);
		counselApplication.setCategory(category);
		counselApplication.setRequirement(requirement);

		System.out.println("제출하겠습니까? (Y/N)");
		String save = inputReader.readLine().trim();
		if (save.equals("Y") || save.equals("y")) {
			if (counselApplicationList.update(counselApplication))
				System.out.println("저장되었습니다.");
			else
				System.out.println("저장되지 않았습니다.");
		}
	}

	private static void showfMaturityList(BufferedReader inputReader, ContractList contractListImpl,
			CustomerList customerList, FamilyHistoryList familyHistoryListImpl, InsuranceList insuranceList,
			PaymentList paymentListImpl, CompensationClaimList compensationClaimList) throws Exception { // 만기계약 대상자 조회
		TargetType targetType = showKeepContract(inputReader); // 계약유지대상자 조회화면 출력 및 대상자 입력 - Enum 반환
		ArrayList<Customer> customerLists = null;

		if (targetType == TargetType.RESURRECT_CANDIDATES) { // 1. 부활 대상자
			customerLists = customerList.getResurrectCandidates(true, contractListImpl.retrieve()); // 부활 대상자들 받아옴
			showResurrectContracts(customerLists); // 부활 대상자 출력
			boolean isShowDetail = getCustomerDetails(inputReader); // 세부정보 보기 출력
			if (!isShowDetail)
				return;
			Customer customer = getCustomerFromResurrect(customerList, inputReader); // 부활 대상자에서 고객 조회
			if (customer == null) {
				System.out.println("입력하신 고객 정보가 없습니다.");
				return;
			}
			showCustomerDetailInfos(customer, familyHistoryListImpl, contractListImpl, insuranceList); // 고객 세부정보 출력
			// 리스트에서 지운다 -> 전체 계약 목록
			if (!selectCustomerDelete(inputReader))
				return;
			customerList.deleteResurrectCandidatesCustomer(customer); // resurrect배열에서 해당 고객 삭제
			contractListImpl.setResurrectFromCustomer(customer.getCustomerID());
			// contract 배열에서 해당 고객ID와 맞는 계약의 resurrect 번호를1->0
			System.out.println("대상자에서 제외되었습니다.");
		} else if (targetType == TargetType.EXPIRED_CONTRACTS) { // 2. 만기계약자
			customerLists = customerList.getExpiredContracts(true, contractListImpl.retrieve()); // 만기계약 대상자들 받아옴
			showExpiredContracts(customerLists); // 만기계약 대상자 출력
			boolean isShowDetail = getCustomerDetails(inputReader); // 세부정보 보기 출력
			if (!isShowDetail)
				return;
			Customer customer = getCustomerFromExpired(customerList, inputReader); // 만기계약 대상자에서 고객 조회
			if (customer == null) {
				System.out.println("입력하신 고객 정보가 없습니다.");
				return;
			}
			showCustomerDetailInfos(customer, familyHistoryListImpl, contractListImpl, insuranceList); // 고객 세부정보 출력
			// 리스트에서 지운다 -> 전체 계약 목록
			if (!selectCustomerDelete(inputReader))
				return;
			customerList.deleteExpiredCustomer(customer);
			contractListImpl.setMaturityFromCustomer(customer.getCustomerID());
			System.out.println("대상자에서 제외되었습니다.");
		} else if (targetType == TargetType.UNPAID_CUSTOMERS) {
			showUnpaidCustomer(inputReader, contractListImpl, customerList, insuranceList, familyHistoryListImpl,
					paymentListImpl, compensationClaimList);
		}
	}

	private static boolean selectCustomerDelete(BufferedReader inputReader) throws IOException {
		System.out.println("해당 대상자를 지우겠습니까?");
		System.out.print("1. 예 2. 아니오  ");
		return inputReader.readLine().trim().equals("1");
	}

	private static TargetType showKeepContract(BufferedReader inputReader) throws IOException {
		System.out.println("계약유지 대상자를 선택해 주세요");
		System.out.println("1. 부활대상자");
		System.out.println("2. 만기계약자");
		System.out.println("3. 보험료 미납자");
		System.out.print("입력 : ");
		switch (inputReader.readLine().trim()) {
		case "1":
			return TargetType.RESURRECT_CANDIDATES;
		case "2":
			return TargetType.EXPIRED_CONTRACTS;
		case "3":
			return TargetType.UNPAID_CUSTOMERS;
		}
		return null;
	}

	private static void showResurrectContracts(ArrayList<Customer> customerList) {
		System.out.println("===============================");
		System.out.println("       부활 대상자 리스트      ");
		System.out.println("===============================");

		System.out.printf("%-10s %-15s %-5s%n", "아이디", "이름", "성별");
		System.out.println("===============================");

		for (Customer customer : customerList) {
			System.out.printf("%-10s %-15s %-5s%n", customer.getCustomerID(), customer.getCustomerName(),
					customer.getEGender().getGenderStr());
		}
	}

	private static void showExpiredContracts(ArrayList<Customer> customerList) {
		System.out.println("===============================");
		System.out.println("        만기 계약자 리스트       ");
		System.out.println("===============================");

		System.out.printf("%-10s %-15s %-5s%n", "아이디", "이름", "성별");
		System.out.println("===============================");

		for (Customer customer : customerList) {
			System.out.printf("%-10s %-15s %-5s%n", customer.getCustomerID(), customer.getCustomerName(),
					customer.getEGender().getGenderStr());
		}
	}

	private static void showUnPaidContracts(ArrayList<Customer> customerList) {
		System.out.println("미납 대상자 리스트");
		for (Customer customer : customerList) {
			System.out.println("아이디: " + customer.getCustomerID() + ", 이름: " + customer.getCustomerName() + ", 성별: "
					+ customer.getEGender().getGenderStr());
		}
	}

	private static Customer getCustomerFromUnpaid(CustomerList customerListImpl, BufferedReader inputReader)
			throws NumberFormatException, IOException {
		System.out.println("세부정보를 확인할 고객의 아이디를 입력하세요");
		System.out.print("아이디 : ");
		String id = inputReader.readLine().trim();
		return customerListImpl.retrieveCustomerFromUnpaid(id);
	}

	private static Customer getCustomerFromExpired(CustomerList customerListImpl, BufferedReader inputReader)
			throws NumberFormatException, IOException {
		System.out.println("세부정보를 확인할 고객의 아이디를 입력하세요");
		System.out.print("아이디 : ");
		String id = inputReader.readLine().trim();
		return customerListImpl.retrieveCustomerFromExpired(id);
	}

	private static Customer getCustomerFromResurrect(CustomerList customerListImpl, BufferedReader inputReader)
			throws NumberFormatException, IOException {
		System.out.println("세부정보를 확인할 고객의 아이디를 입력하세요");
		System.out.print("아이디 : ");
		String id = inputReader.readLine().trim();
		return customerListImpl.retrieveCustomerFromResurrect(id);
	}

	private static void showCustomerList(CustomerList customerListImpl, BufferedReader inputReader,
			FamilyHistoryList familyHistoryListImpl, ContractList contractListImpl, InsuranceList insuranceList,
			PaymentList paymentListImpl, CompensationClaimList compensationClaimList) throws Exception {
		System.out.println("==================================");
		System.out.println("           고객 조회 메뉴           ");
		System.out.println("==================================");
		System.out.println("1. 고객 정보 조회");
		System.out.println("2. 계약 유지 대상자 조회");
		System.out.println("----------------------------------");

		String userChoice = "";
		userChoice = inputReader.readLine().trim();

		switch (userChoice) {
		case "1":
			System.out.println("==================================");
			System.out.println("         [고객 정보 조회]           ");
			System.out.println("==================================");

			do {
				userChoice = inputCustomerId(inputReader); // 아이디 받아옴
				if (userChoice.equals(""))
					System.out.println("조건을 기입했는지 체크해주세요.");
			} while (userChoice.equals(""));

			String id = userChoice;
			Customer customer = customerListImpl.retrieveCustomer(id); // 아이디에 따른 고객정보 받아옴

			if (customer == null) {
				System.out.println("조건에 맞는 고객 정보가 하나도 없습니다.\n조건을 확인해주세요.");
				return;
			}

			showCustomerInfo(customer); // id 이름 생일 성별

			boolean isRetrieveDetail = getCustomerDetails(inputReader); // 세부정보 읽을지 확인

			if (isRetrieveDetail)
				showCustomerDetailInfos(customer, familyHistoryListImpl, contractListImpl, insuranceList); // 고객 세부정보 출력
			else
				return;

			if (inputDelOrUpd(inputReader)) {
				customerListImpl.delete(id); // 삭제 or 업뎃 여부 입력 후 해당 고객 삭제
				System.out.println("고객 정보가 삭제되었습니다.");
			} else {
				Customer newCustomer = getUpdatedCustomer(inputReader);
				customerListImpl.update(newCustomer, id);
				// 고객 정보 입력 받아서 해당 고객 업데이트
				System.out.println("고객 정보가 저장되었습니다.");
			}

			break;

		case "2":
			System.out.println("==================================");
			System.out.println("     [계약 유지 대상자 조회]      ");
			System.out.println("==================================");

			showfMaturityList(inputReader, contractListImpl, customerListImpl, familyHistoryListImpl, insuranceList,
					paymentListImpl, compensationClaimList);
			break;
		}
	}

	private static boolean inputDelOrUpd(BufferedReader inputReader) throws IOException {
		System.out.println("==========================");
		System.out.println("   고객 정보 수정 및 삭제   ");
		System.out.println("==========================");
		System.out.println("1. 고객 정보 삭제");
		System.out.println("2. 고객 정보 수정");
		System.out.println("--------------------------");
		System.out.print("문구를 선택해 주세요: ");
		return inputReader.readLine().trim().toLowerCase().equals("1");
	}

	private static Customer getUpdatedCustomer(BufferedReader inputReader) throws IOException {
		Customer upCustomer = new Customer();
		System.out.println("=============================");
		System.out.println("      고객 정보 수정하기      ");
		System.out.println("=============================");
		System.out.print("고객 이름: ");
		upCustomer.setCustomerName(inputReader.readLine().trim());
		System.out.print("고객 성별(남/여): ");
		upCustomer.setEGender(inputReader.readLine().trim().equals("남") ? EGender.male : EGender.female);
		System.out.print("고객 생년월일: ");
		upCustomer.setBirth(inputReader.readLine().trim());
		System.out.print("고객 전화번호: ");
		upCustomer.setPnumber(inputReader.readLine().trim());
		System.out.print("고객 주소: ");
		upCustomer.setAddress(inputReader.readLine().trim());
		System.out.print("고객 직업: ");
		upCustomer.setJob(inputReader.readLine().trim());
		return upCustomer;
	}

	private static void showCustomerDetailInfos(Customer customer, FamilyHistoryList familyHistoryListImpl,
			ContractList contractListImpl, InsuranceList insuranceList) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===============================");
		System.out.println("        고객 세부 정보         ");
		System.out.println("===============================");
		System.out.println("[추가 정보]");
		showCustomerInfoDetail(customer); // 전번 주소 직업
		System.out.println();
		System.out.println("[가족력 정보]");
		showFamilyHistory(familyHistoryListImpl.getFamilyHistoryFromId(customer.getCustomerID()));
		// 가족력 리스트(질환명,가족관계)
		System.out.println();
		System.out.println("[계약 및 보험 정보]");
		List<Contract> selectedContracts = new ArrayList<Contract>();
		ArrayList<Contract> contracts = contractListImpl.retrieve();
		for (Contract contract : contracts) {
			if (contract.getCustomerID().equals(customer.getCustomerID())) {
				selectedContracts.add(contract);
			}
		}
		List<Insurance> selectedInsurances = new ArrayList<Insurance>();
		for (Contract contract : contracts) { // 계약된 보험 아이디가 여기 있음
			Insurance insurance = insuranceList.retrieveInsuranceDetail(contract.getInsuranceID());
			selectedInsurances.add(insurance);
		}
		System.out.println();
		showInsuranceList(selectedContracts, selectedInsurances);
		// 보유 계약 리스트(보험명/만기 여부/해지여부/납입 여부)
	}

	private static void showInsuranceList(List<Contract> contracts, List<Insurance> insurances) {
		System.out.println("========================================");
		System.out.println("         계약 및 보험 정보                ");
		System.out.println("========================================");
		System.out.printf("%-15s %-12s %-12s %-12s%n", "보험명", "만기 여부", "해지 여부", "부활 여부");
		System.out.println("----------------------------------------");

		for (Contract contract : contracts) {
			Insurance selectedInsurance = null;
			for (Insurance insurance : insurances) {
				if (contract.getInsuranceID().equals(insurance.getInsuranceID())) {
					selectedInsurance = insurance;
					break;
				}
			}

			System.out.printf("%-15s %-12s %-12s %-12s%n", selectedInsurance.getInsuranceName(),
					(contract.isMaturity() ? "예" : "아니오"), (contract.isCancellation() ? "예" : "아니오"),
					(contract.isResurrection() ? "예" : "아니오"));
		}
	}

	private static void showFamilyHistory(FamilyHistory familyHistory) {
		System.out.println("==============================");
		System.out.println("         가족력 정보         ");
		System.out.println("==============================");
		System.out.printf("%-15s %-15s%n", "질환명", "가족관계");
		System.out.println("-----------------------------");
		System.out.printf("%-15s %-15s%n", familyHistory.getDiseaseName(), familyHistory.getRelationship());
	}

	private static boolean getCustomerDetails(BufferedReader inputReader) throws IOException {
		System.out.println("===============================");
		System.out.println("     고객 세부정보 조회 여부     ");
		System.out.println("===============================");
		System.out.println("고객 세부정보를 조회하시겠습니까?");
		System.out.println("세부 정보 조회를 희망하시면 Yes 입력해주세요");
		System.out.println("그렇지 않으시면 No 입력해주세요");
		System.out.println("---------------------------------");
		System.out.print("입력: ");
		return inputReader.readLine().trim().toLowerCase().equals("yes");
	}

	private static void showCustomerInfo(Customer customer) {
		System.out.println("===============================");
		System.out.println("         고객 정보 조회         ");
		System.out.println("===============================");
		System.out.printf("%-10s %-10s%n", "아이디", customer.getCustomerID());
		System.out.printf("%-10s %-10s%n", "이름", customer.getCustomerName());
		System.out.printf("%-10s %-10s%n", "생년월일", customer.getBirth());
		System.out.printf("%-10s %-10s%n", "성별", customer.getEGender().getGenderStr());
	}

	private static void showCustomerInfoDetail(Customer customer) {
		System.out.println("===============================");
		System.out.println("       추가 정보 조회          ");
		System.out.println("===============================");
		System.out.printf("%-12s %-12s%n", "전화 번호", customer.getPnumber());
		System.out.printf("%-12s %-12s%n", "주소", customer.getAddress());
		System.out.printf("%-12s %-12s%n", "직업", customer.getJob());
	}

	private static String inputCustomerId(BufferedReader inputReader) throws IOException {
		System.out.print("고객 아이디를 입력해 주세요 : ");
		return inputReader.readLine().trim();
	}

	private static String inputCounselId(BufferedReader inputReader) throws IOException {
		System.out.print("상담 아이디를 입력해 주세요 : ");
		return inputReader.readLine().trim();
	}

	private static void createCompensationClaim(InsuranceList insuranceList,
			CompensationClaimList compensationClaimList, CarAccidentList carAccidentList, ContractList contractList,
			CustomerList customerList, BufferedReader inputReader) throws Exception {
		CompensationClaim compensationClaim = new CompensationClaim();
		System.out.println("****************** Compensation Claim *******************");
		System.out.println("개인정보 확인을 위해 고객ID를 입력해주세요");
		String inputCustomerId = inputReader.readLine().trim();
		if (customerList.getCustomerByID(inputCustomerId) == null)
			System.out.println("존재하지 않는 ID입니다");
		else {
			System.out.println("고객님이 가입하신 보험 정보는 아래와 같습니다.\n" + "보험금을 청구할 보험ID를 입력하세요.");
			ArrayList<Contract> contracts = contractList.getContractsByCID(inputCustomerId);
			for (int i = 0; i < contracts.size(); i++) {
				Contract contract = contractList.getContractsByCID(inputCustomerId).get(i);
				Insurance insurance = insuranceList.getInsurancebyId(contract.getInsuranceID());
				System.out.println(
						insurance.getInsuranceID() + " " + insurance.getType() + " " + insurance.getInsuranceName());
			}
			String inputInsuranceId = inputReader.readLine().trim();
			if (contractList.getContractByInsuranceID(inputInsuranceId) == null)
				System.out.println("존재하지 않는 ID입니다");
			else {
				compensationClaim.setCustomerID(inputCustomerId);
				compensationClaim.setInsuranceID(inputInsuranceId);
				compensationClaim
						.setCCID("CC" + compensationClaim.getInsuranceID() + compensationClaim.getCustomerID());
				if (compensationClaimList.getCompensationClaimbyID(compensationClaim.getCCID()) != null) {
					System.out.println("해당 보험에 대한 기존 청구건이 아직 심사완료되지 않았습니다. 기존 청구건의 심사가 끝난 뒤 다시 이용바랍니다.");

				} else {
					Insurance selectedInsurance = insuranceList
							.retrieveInsuranceDetail(compensationClaim.getInsuranceID());
					System.out.println("보험종류: " + selectedInsurance.getType() + "  보험명: "
							+ selectedInsurance.getInsuranceName() + "\n" + "보험금 청구를 위한 서류 목록(하나의 압축파일로 업로드)\n"
							+ "1. 보험금 청구서" + "  2. 개인(신용)정보처리동의서" + "  3. 수익자 신분증(앞면) 사본" + "  4. 수익자 통장 사본\n"
							+ "보험종류가 자동차보험인 경우, 5. 교통사고 사실 확인원  6. 교통사고신속처리협의서 추가 제출");
					System.out.print("접수자명: ");
					compensationClaim.setReceptionistName(inputReader.readLine().trim());
					System.out.print("접수자 전화번호: ");
					compensationClaim.setReceptionistPNumber(inputReader.readLine().trim());
					System.out.print("보험계약자와의 관계(예:부): ");
					compensationClaim.setRelationshipOfContractor(inputReader.readLine().trim());
					System.out.print("구비서류 업로드란: ");
					compensationClaim.setDocumentFilePath(inputReader.readLine().trim());
					System.out.print("은행: ");
					compensationClaim.setBank(inputReader.readLine().trim());
					System.out.print("계좌번호: ");
					compensationClaim.setAccountNumber(inputReader.readLine().trim());
					System.out.print("예금주명: ");
					compensationClaim.setAccountHolderName(inputReader.readLine().trim());
					if (insuranceList.getInsurancebyId(compensationClaim.getInsuranceID()).getType().equals("Car")) {
						CarAccident carAccident = createCarAccident(compensationClaim, inputReader);
						if (compensationClaimList.createCompensationClaim(compensationClaim)
								&& carAccidentList.createCarAccident(carAccident))
							System.out.println(
									compensationClaim.getCCID() + "신청이 완료되었습니다. 심사 결과에 따라 보상이 제한되거나 거절될 수 있습니다.");
						else
							System.out.println("신청에 실패하였습니다. 다시 시도해주십시오.");
					} else {
						if (compensationClaimList.createCompensationClaim(compensationClaim)) {
							System.out.println(
									compensationClaim.getCCID() + "신청이 완료되었습니다. 심사 결과에 따라 보상이 제한되거나 거절될 수 있습니다.");
						} else
							System.out.println("신청에 실패하였습니다. 다시 시도해주십시오.");
					}
				}

			}
		}
	}

	private static void retrieveCompensationClaim(InsuranceList insuranceList,
			CompensationClaimList compensationClaimList, CarAccidentList carAccidentList, SurveyList surveyList,
			BufferedReader inputReader) throws Exception {
		System.out.println("****************** Compensation Claim List *******************");
		System.out.println("청구ID 보험ID 고객ID 접수자명 접수자전화번호 보험계약자와의 관계 구비서류파일경로 은행 계좌번호 예금주명");
		showList(compensationClaimList.retrieve());
		System.out.println("손해사정 내용을 조회하시겠습니까? (Y/N)");
		String userChoice = inputReader.readLine().trim();
		switch (userChoice) {
		case "Y":
			System.out.println("청구ID를 입력하세요: ");
			String inputCCID = inputReader.readLine().trim();
			if (compensationClaimList.getCompensationClaimbyID(inputCCID) == null)
				System.out.println("존재하지 않는 ID입니다");
			else
				retrieveSurvey(compensationClaimList, carAccidentList, surveyList, insuranceList, inputCCID,
						inputReader);
			break;
		case "N":
			System.out.println("초기화면으로 돌아갑니다.");
			break;
		}
	}

	private static void retrieveSurvey(CompensationClaimList compensationClaimList, CarAccidentList carAccidentList,
			SurveyList surveyList, InsuranceList insuranceList, String CCID, BufferedReader inputReader)
			throws Exception {
		Survey survey = surveyList.getSurveyByID(CCID);
		if (!(survey == null)) {
			System.out.println("청구ID: " + survey.getCCID() + "\n담당자명:" + survey.getManagerName() + "\n조사보고서: "
					+ survey.getReportFilePath() + "\n손해사정료: " + survey.getSurveyFee() + "\n결정보험금: "
					+ survey.getDecisionMoney() + "\n면/부책(면책:false, 부책:true): " + survey.isResponsibility()
					+ "\n면/부책 사유: " + survey.getResponsibilityReason());
			System.out.println("1. 손해사정 데이터 수정\n2. 결정보험금 지급하기");
			switch (inputReader.readLine().trim()) {
			case "1":
				modifySurvey(surveyList, survey, inputReader);
				break;
			case "2":
				CompensationClaim compensationClaim = compensationClaimList.getCompensationClaimbyID(survey.getCCID());
				System.out.println(
						compensationClaim.getReceptionistName() + " " + compensationClaim.getReceptionistPNumber() + " "
								+ insuranceList.getInsurancebyId(compensationClaim.getInsuranceID()).getInsuranceName()
								+ " " + compensationClaim.getBank() + " " + compensationClaim.getAccountNumber() + " "
								+ compensationClaim.getAccountHolderName() + " " + survey.getDecisionMoney() + "원");
				requestBanking();
				compensationClaimList.delete(compensationClaim.getCCID());
				if (insuranceList.getInsurancebyId(compensationClaim.getInsuranceID()).getType() == "Car") {
					CarAccident carAccident = carAccidentList.getCarAccidentByID(compensationClaim.getCCID());
					carAccidentList.delete(carAccident.getCCID());
					break;
				}
			}
		} else {
			System.out.println("손해사정된 내용이 없습니다. 손해사정 데이터를 입력하시겠습니까? (Y/N)");
			switch (inputReader.readLine().trim()) {
			case "Y":
				createSurvey(compensationClaimList, carAccidentList, surveyList, insuranceList, CCID, inputReader);
				break;
			case "N":
				System.out.println("초기화면으로 돌아갑니다.");
				break;
			}
		}
	}

	private static void modifySurvey(SurveyList surveyList, Survey survey, BufferedReader inputReader)
			throws Exception {
		String choice = "";
		System.out.println("수정할 정보를 선택하고 내용을 입력하세요.");
		System.out.println("1. 담당자명, 2. 조사보고서 업로드, 3. 손해사정료, 4. 결정보험금, 5. 면/부책(면책:false, 부책:true), 6. 면/부책 사유");
		System.out.print("수정할 정보 : ");
		choice = inputReader.readLine().trim();
		System.out.print("수정할 내용 :");
		String content = inputReader.readLine().trim();
		switch (choice) {
		case ("1"):
			survey.setManagerName(content);
			break;
		case ("2"):
			survey.setReportFilePath(content);
			break;
		case ("3"):
			if (isInteger(content))
				survey.setSurveyFee(Integer.valueOf(content));
			else
				System.out.println("숫자로 입력되지 않아 수정이 저장되지 않았습니다.");
			break;
		case ("4"):
			if (isInteger(content))
				survey.setDecisionMoney(Integer.valueOf(content));
			else
				System.out.println("숫자로 입력되지 않아 수정이 저장되지 않았습니다.");
			break;
		case ("5"):
			if (content.equals("true"))
				survey.setResponsibility(Boolean.parseBoolean(content));
			else if (content.equals("false"))
				survey.setResponsibility(Boolean.parseBoolean(content));
			else
				System.out.println("true또는 false로 입력되지 않아 수정이 저장되지 않았습니다.");
			break;
		case ("6"):
			survey.setResponsibilityReason(content);
			break;
		default:
			System.out.println("올바르지 않은 선택지입니다.");
		}
		if (surveyList.updateSurvey(survey))
			System.out.println("수정이 완료되었습니다.");
		else
			System.out.println("수정에 실패했습니다.");
	}

	private static void showList(ArrayList<?> dataList) {
		String list = "";
		for (int i = 0; i < dataList.size(); i++) {
			list += dataList.get(i) + "\n";
		}
		System.out.println(list);
	}

	private static void createSurvey(CompensationClaimList compensationClaimList, CarAccidentList carAccidentList,
			SurveyList surveyList, InsuranceList insuranceList, String CCID, BufferedReader inputReader)
			throws Exception {
		Survey survey = new Survey();
		System.out.println("****************** Survey *******************");
		survey.setCCID(CCID);
		CompensationClaim compensationClaim = compensationClaimList.getCompensationClaimbyID(survey.getCCID());
		System.out.print("담당자명: ");
		survey.setManagerName(inputReader.readLine().trim());
		System.out.print("조사보고서 업로드: ");
		survey.setReportFilePath(inputReader.readLine().trim());
		System.out.print("손해사정료: ");
		survey.setSurveyFee(Integer.parseInt(inputReader.readLine().trim()));
		System.out.print("결정보험금: ");
		survey.setDecisionMoney(Integer.parseInt(inputReader.readLine().trim()));
		System.out.print("면/부책 (면책은 false, 부책은 true를 입력하세요) : ");
		survey.setResponsibility(Boolean.parseBoolean(inputReader.readLine().trim()));
		System.out.print("면/부책 사유 : ");
		survey.setResponsibilityReason(inputReader.readLine().trim());
		if (surveyList.createSurvey(survey)) {
			System.out.println("입력이 완료되었습니다.");
			System.out.println("결정보험금(" + survey.getDecisionMoney() + "원)을 지급요청하시겠습니까? (Y/N)");
			if (inputReader.readLine().trim().equals("Y")) {
				System.out.println(
						compensationClaim.getReceptionistName() + " " + compensationClaim.getReceptionistPNumber() + " "
								+ insuranceList.getInsurancebyId(compensationClaim.getInsuranceID()).getInsuranceName()
								+ " " + compensationClaim.getBank() + " " + compensationClaim.getAccountNumber() + " "
								+ compensationClaim.getAccountHolderName() + " " + survey.getDecisionMoney() + "원");
				if (requestBanking()) {
					compensationClaimList.delete(compensationClaim.getCCID()); // compensationClaim, survey 삭제
					if (insuranceList.getInsurancebyId(compensationClaim.getInsuranceID()).getType() == "Car") {
						CarAccident carAccident = carAccidentList.getCarAccidentByID(compensationClaim.getCCID());
						carAccidentList.delete(carAccident.getCCID());
					}
				}
			} else
				System.out.println("초기화면으로 돌아갑니다.");
		} else
			System.out.println("신청에 실패하였습니다. 다시 시도해주십시오.");
	}

	private static boolean requestBanking() throws IOException {
		System.out.println("이체 요청을 완료했습니다. 보험금이 입금되기까지는 수일이 소요될 수 있습니다.");
		return true;
	}

	private static CarAccident createCarAccident(CompensationClaim compensationClaim, BufferedReader inputReader)
			throws IOException {
		CarAccident carAccident = new CarAccident();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
		System.out.println("****************** Compensation Claim - Car Accident*******************");
		carAccident.setCCID(compensationClaim.getCCID());
		System.out.print("사고유형: ");
		carAccident.setType(inputReader.readLine().trim());
		System.out.print("사고일시 (형식 예시-2023년 01월 01일 17시 30분): ");
		carAccident.setDateTime(LocalDateTime.parse(inputReader.readLine().trim(), dateTimeFormatter));
		System.out.print("사고장소: ");
		carAccident.setPlace(inputReader.readLine().trim());
		System.out.print("차량번호: ");
		carAccident.setCarNumber(inputReader.readLine().trim());
		System.out.print("운전자 성명: ");
		carAccident.setDriverName(inputReader.readLine().trim());
		System.out.print("면허번호: ");
		carAccident.setLicenseNumber(inputReader.readLine().trim());
		System.out.print("사고 내용: ");
		carAccident.setAccidentDetail(inputReader.readLine().trim());
		return carAccident;
	}

	private static void designInsurance(InsuranceList insuranceListImpl, TermsList termsListImpl,
			CustomerList customerList, FamilyHistoryList familyHistoryList, GuaranteeList guaranteeList,
			BufferedReader inputReader, InsuranceApplicationList insuranceApplicationList) throws Exception {

		String choice = "";
		while (true) {
			System.out.println("****************** 보험 설계 화면 *******************");
			System.out.println("1. 설계 보험 관리 2. 새 보험 설계 3. 약관 관리 4. 판매중인 보험 조회 x. 종료");
			System.out.println("선택 : ");
			choice = inputReader.readLine().trim();

			if (choice.equals("1"))
				showDesignedInsurance(insuranceListImpl, termsListImpl, guaranteeList, inputReader);
			else if (choice.equals("2"))
				createInsurance(insuranceListImpl, termsListImpl, inputReader);
			else if (choice.equals("3"))
				termsManagement(insuranceListImpl, termsListImpl, inputReader);
			else if (choice.equals("4"))
				showOnSaleInsurance(insuranceListImpl, insuranceApplicationList, customerList, familyHistoryList,
						guaranteeList, termsListImpl, inputReader, "Manager");
			else if (choice.equals("x"))
				break;
			else
				System.out.println("잘못된 선택지입니다.");
		}
	}

	private static void termsManagement(InsuranceList insuranceListImpl, TermsList termsListImpl,
			BufferedReader inputReader) throws Exception {
		while (true) {
			System.out.println("****************** 약관 관리 화면 *******************");
			System.out.println("1. 약관 조회 2. 새 약관 등록 x. 종료");
			System.out.println("선택 : ");
			String choice = inputReader.readLine().trim();
			if (choice.equals("1"))
				showList(termsListImpl.retrieveAllTerms());
			else if (choice.equals("2"))
				createTerms(termsListImpl, inputReader);
			else if (choice.equals("x"))
				break;
			else
				System.out.println("잘못된 선택지입니다.");
		}
	}

	private static void createTerms(TermsList termsListImpl, BufferedReader inputReader) throws Exception {

		Terms terms = new Terms();
		System.out.println("--------약관 추가 화면---------");
		System.out.println("약관 ID : ");
		terms.setTermsID(inputReader.readLine().trim());
		System.out.println("약관명  : ");
		terms.setTermsName(inputReader.readLine().trim());
		System.out.println("보장내용 : ");
		terms.setTermsContent(inputReader.readLine().trim());
		System.out.println("지급보험금 산정방식: ");
		terms.setCalculatedMoneyMethod(inputReader.readLine().trim());
		System.out.println("입력한 내용을 저장하시겠습니까? (Y/N)");
		while (true) {
			System.out.println("선택 : ");
			String save = inputReader.readLine().trim();
			if (save.equals("Y") && terms.checkAllFillIn() == true) {
				if (termsListImpl.createTerms(terms))
					System.out.println("저장되었습니다.");
				else
					System.out.println("저장되지 않았습니다.");
				break;
			} else if (save.equals("Y") && terms.checkAllFillIn() == false) {
				System.out.println("입력하지 않은 항목이 있습니다. 모든 항목을 입력해 주세요");
				break;
			} else if (save.equals("N")) {
				System.out.println("저장을 취소했습니다.");
				break;
			} else
				System.out.println("Y/N중 하나는 입력하셔야합니다.");
		}

	}

	private static void showDesignedInsurance(InsuranceList insuranceListImpl, TermsList termsList,
			GuaranteeList guaranteeList, BufferedReader inputReader) throws Exception {
		while (true) {
			System.out.println("****************** 설계 보험 관리 화면 *******************");
			System.out.println("1. 설계서 수정/삭제하기 2. 보험 등록하기 3. 금융감독원에 인가 요청 x. 종료");
			System.out.println("선택 : ");
			String choice = inputReader.readLine().trim();
			if (choice.equals("1"))
				updateInsuranceDetail(insuranceListImpl, termsList, guaranteeList, inputReader);
			else if (choice.equals("2"))
				registerInsurance(insuranceListImpl, inputReader, guaranteeList, termsList);
			else if (choice.equals("3"))
				requestAuthorization(insuranceListImpl, inputReader, guaranteeList, termsList);
			else if (choice.equals("x"))
				break;
			else
				System.out.println("잘못된 선택지입니다.");
		}
	}

	private static void requestAuthorization(InsuranceList insuranceListImpl, BufferedReader inputReader,
			GuaranteeList guaranteeList, TermsList termsList) throws Exception {
		System.out.println("****************** 인가 요청 화면 *******************");
		System.out.println("================설계 보험 리스트=======================");
		showList(insuranceListImpl.getUnregisteredInsuranceList());
		System.out.println("===================================================");
		System.out.println("인가를 요청할 보험 ID를 입력하세요. 없으면 x를 입력하세요");
		while (true) {
			System.out.print("선택 : ");
			String insuranceID = inputReader.readLine().trim();
			if (!insuranceID.equals("x") && !insuranceID.equals(null)) {
				Insurance insurance = insuranceListImpl.getInsurancebyId(insuranceID);

				System.out.println("보험종류: " + insurance.getType() + "\n보험명: " + insurance.getInsuranceName()
						+ "\n최대보장한도: " + insurance.getMaxCompensation() + "\n보험기간: " + insurance.getPeriodOfInsurance()
						+ "\n납입기간: " + insurance.getPaymentPeriod() + "\n가입나이: " + insurance.getAgeOfTarget()
						+ "\n납입주기: " + insurance.getPaymentCycle() + "\n보장내용(보통약관):");
				ArrayList<Guarantee> guarantees = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID());
				for (int i = 0; i < guarantees.size(); i++) {
					Guarantee guaranteeByIID = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID()).get(i);
					Terms terms = termsList.getTermsByID(guaranteeByIID.getTermsID());
					System.out.println("약관명: " + terms.getTermsName() + "  약관내용: " + terms.getTermsContent());
				}
				System.out
						.println("배당여부: " + insurance.isDistributionStatus() + "\n주의사항: " + insurance.getPrecaution());
				System.out.println("==========================================");
				while (true) {
					System.out.println("인가 요청을 진행할 보험이 확실합니까? (Y/N)");
					System.out.println("선택 : ");
					String choice = inputReader.readLine().trim();
					if (choice.equals("Y")) {
						String insuranceName = insuranceListImpl.requestAuthorization(insuranceID);
						if (!insuranceName.equals(null))
							System.out.println("보험명 : " + insuranceName + "의 인가 신청이 완료되었습니다. 인가 완료까지 수 일이 소요될 수 있습니다.");
						else
							System.out.println("신청에 실패했습니다.");
						break;
					} else if (choice.equals("N")) {
						System.out.println("신청이 취소되었습니다.");
						break;
					} else
						System.out.println("Y/N 중 하나를 입력해주세요");
				}
				break;
			} else if (insuranceID.equals("x")) {
				System.out.println("이전화면으로 돌아갑니다.");
				break;
			} else if (insuranceID.equals(null))
				System.out.println("아무것도 입력되지 않았습니다.");
		}
	}

	private static void registerInsurance(InsuranceList insuranceListImpl, BufferedReader inputReader,
			GuaranteeList guaranteeList, TermsList termsList) throws Exception {
		System.out.println("****************** 보험 등록 화면 *******************");
		System.out.println("================설계 보험 리스트=======================");
		showList(insuranceListImpl.getUnregisteredInsuranceList());
		System.out.println("===================================================");
		System.out.println("판매중으로 등록할 보험 ID를 입력하세요. 없으면 x를 입력하세요");
		System.out.print("선택 : ");
		String insuranceID = inputReader.readLine().trim();
		if (!insuranceID.equals("x") && !insuranceID.equals(null)) {
			Insurance insurance = insuranceListImpl.getInsurancebyId(insuranceID);

			System.out.println("보험종류: " + insurance.getType() + "\n보험명: " + insurance.getInsuranceName() + "\n최대보장한도: "
					+ insurance.getMaxCompensation() + "\n보험기간: " + insurance.getPeriodOfInsurance() + "\n납입기간: "
					+ insurance.getPaymentPeriod() + "\n가입나이: " + insurance.getAgeOfTarget() + "\n납입주기: "
					+ insurance.getPaymentCycle() + "\n보장내용(보통약관):");
			ArrayList<Guarantee> guarantees = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID());
			for (int i = 0; i < guarantees.size(); i++) {
				Guarantee guaranteeByIID = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID()).get(i);
				Terms terms = termsList.getTermsByID(guaranteeByIID.getTermsID());
				System.out.println("약관명: " + terms.getTermsName() + "  약관내용: " + terms.getTermsContent());
			}
			System.out.println("배당여부: " + insurance.isDistributionStatus() + "\n주의사항: " + insurance.getPrecaution());
			System.out.println("==========================================");
			while (true) {
				System.out.println("금융감독원에 의해 인가받은 보험이 확실합니까? (Y/N)");
				System.out.println("선택 : ");
				String choice = inputReader.readLine().trim();
				if (choice.equals("Y")) {
					if (insuranceListImpl.updateAuthorization(insuranceID, true))
						System.out.println("신규 보험 등록이 완료되었습니다.");
					else
						System.out.println("존재하지 않는 보험ID입니다.");
					break;
				} else if (choice.equals("N")) {
					System.out.println("신청이 취소되었습니다.");
					break;
				} else
					System.out.println("Y/N 중 하나를 입력해주세요");
			}
		} else if (insuranceID.equals(null))
			System.out.println("아무것도 입력되지 않았습니다.");
		else if (insuranceID.equals("x"))
			System.out.println("보험 등록을 종료합니다.");
	}

	private static void showOnSaleInsurance(InsuranceList insuranceList,
			InsuranceApplicationList insuranceApplicationList, CustomerList customerList,
			FamilyHistoryList familyHistoryList, GuaranteeList guaranteeList, TermsList termsList,
			BufferedReader inputReader, String who) throws Exception {
		String insuranceType = "";
		while (true) {
			System.out.println("****************** 보험 조회 화면 *******************");
			System.out.println("조회하실 보험 종류를 입력하세요");
			System.out.println("1. 전체, 2. 자동차보험, 3. 건강보험, 4. 종신보험, 5. 화재보험 6. 재물보험 x. 조회 종료");
			System.out.println("선택 : ");
			insuranceType = inputReader.readLine().trim();
			if (insuranceType.equals("1"))
				showList(insuranceList.getOnSaleInsuranceList());
			else if (insuranceType.equals("2"))
				showList(insuranceList.retrieveInsurance("Car"));
			else if (insuranceType.equals("3"))
				showList(insuranceList.retrieveInsurance("Health"));
			else if (insuranceType.equals("4"))
				showList(insuranceList.retrieveInsurance("Life"));
			else if (insuranceType.equals("5"))
				showList(insuranceList.retrieveInsurance("Fire"));
			else if (insuranceType.equals("6"))
				showList(insuranceList.retrieveInsurance("Property"));
			else if (!insuranceType.equals("x"))
				System.out.println("잘못된 선택지입니다.");
			else if (insuranceType.equals("x"))
				break;
			if (who.equals("Customer")) {
				System.out.println("보험 신청 메뉴로 이동하시겠습니까? (Y/N)");
				String choice = inputReader.readLine().trim();
				if (choice.equals("Y") || choice.equals("y")) {
					System.out.println("가입 신청할 보험ID를 입력하세요");
					String inputInsuranceId = inputReader.readLine().trim();
					if (insuranceList.getInsurancebyId(inputInsuranceId) == null)
						System.out.println("존재하지 않는 ID입니다");
					else {
						Insurance insurance = insuranceList.getInsurancebyId(inputInsuranceId);
						System.out.println("********** 보험 정보 **********");
						System.out.println("보험종류: " + insurance.getType() + "\n보험명: " + insurance.getInsuranceName()
								+ "\n최대보장한도: " + insurance.getMaxCompensation() + "\n보험기간: "
								+ insurance.getPeriodOfInsurance() + "\n납입기간: " + insurance.getPaymentPeriod()
								+ "\n가입나이: " + insurance.getAgeOfTarget() + "\n납입주기: " + insurance.getPaymentCycle()
								+ "\n보장내용(보통약관):");
						ArrayList<Guarantee> guarantees = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID());
						for (int i = 0; i < guarantees.size(); i++) {
							Guarantee guaranteeByIID = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID())
									.get(i);
							Terms terms = termsList.getTermsByID(guaranteeByIID.getTermsID());
							System.out.println("약관명: " + terms.getTermsName() + "  약관내용: " + terms.getTermsContent());
						}
						System.out.println(
								"배당여부: " + insurance.isDistributionStatus() + "\n주의사항: " + insurance.getPrecaution());
						createInsuranceApplication(insurance, insuranceApplicationList, customerList, familyHistoryList,
								guaranteeList, termsList, inputReader);

					}
				}
			} else {
				System.out.println("이전 화면으로 돌아갑니다.");
			}
		}
	}

	private static void createInsuranceApplication(Insurance insurance,
			InsuranceApplicationList insuranceApplicationList, CustomerList customerList,
			FamilyHistoryList familyHistoryList, GuaranteeList guaranteeList, TermsList termsList,
			BufferedReader inputReader) throws Exception {
		InsuranceApplication insuranceApplication = new InsuranceApplication();
		insuranceApplication.setInsuranceID(insurance.getInsuranceID());
		insuranceApplication.setCreatedAt(LocalDate.now());
		System.out.println("****************** Insurance Application *******************");
		System.out.println("고객ID를 입력해주세요");
		String inputCustomerId = inputReader.readLine().trim();
		if (customerList.getCustomerByID(inputCustomerId) == null)
			System.out.println("존재하지 않는 ID입니다");
		else {
			Customer customer = customerList.getCustomerByID(inputCustomerId);
			insuranceApplication.setCustomerID(customer.getCustomerID());
			insuranceApplication
					.setApplicationID(insuranceApplication.getInsuranceID() + insuranceApplication.getCustomerID());
			System.out.print("보험 기간: ");
			insuranceApplication.setInsurancePeriod(inputReader.readLine().trim());
			System.out.print("납입 주기: ");
			insuranceApplication.setPaymentCycle(inputReader.readLine().trim());
			System.out.print("청약서 업로드: ");
			insuranceApplication.setSubscriptionFilePath(inputReader.readLine().trim());
			if (showTermsAndConditions(customer, insurance, insuranceApplication, familyHistoryList, guaranteeList,
					termsList, inputReader)) {
				if (insuranceApplicationList.createInsuranceApplication(insuranceApplication)) {
					System.out.println("신청이 완료되었습니다. 심사 결과에 따라 최대보장한도 또는 보험료가 제한되거나 가입이 불가능할 수 있습니다.");
				} else
					System.out.println("신청에 실패하였습니다. 다시 시도해주십시오.");
			} else
				System.out.println("약관에 동의하지 않으면 가입 신청이 불가능합니다. 약관을 읽고 동의란에 체크해주세요");
		}
	}

	private static boolean showTermsAndConditions(Customer customer, Insurance insurance,
			InsuranceApplication insuranceApplication, FamilyHistoryList familyHistoryList, GuaranteeList guaranteeList,
			TermsList termsList, BufferedReader inputReader) throws Exception {
		System.out.println("********** 보험 약관 안내 **********");
		System.out.println("--------보험 정보--------");

		System.out.println("보험종류: " + insurance.getType() + "\n보험명: " + insurance.getInsuranceName() + "\n최대보장한도: "
				+ insurance.getMaxCompensation() + "\n보험기간: " + insurance.getPeriodOfInsurance() + "\n납입기간: "
				+ insurance.getPaymentPeriod() + "\n가입나이: " + insurance.getAgeOfTarget() + "\n납입주기: "
				+ insurance.getPaymentCycle() + "\n보장내용(보통약관)");

		ArrayList<Guarantee> guarantees = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID());
		for (int i = 0; i < guarantees.size(); i++) {
			Guarantee guaranteeByIID = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID()).get(i);
			Terms terms = termsList.getTermsByID(guaranteeByIID.getTermsID());
			System.out.println("약관명: " + terms.getTermsName() + "  약관내용: " + terms.getTermsContent());
		}
		System.out.println("배당여부: " + insurance.isDistributionStatus() + "\n주의사항: " + insurance.getPrecaution());
		System.out.println("선택한 보험 기간: " + insuranceApplication.getInsurancePeriod() + "  선택한 납입 주기: "
				+ insuranceApplication.getPaymentCycle());
		System.out.println("\n--------고객 정보--------");
		System.out.println("이름: " + customer.getCustomerName() + "  성별: " + customer.getEGender() + "  생년월일: "
				+ customer.getBirth() + "\n전화번호: " + customer.getPnumber() + "  주소: " + customer.getAddress() + "  직업: "
				+ customer.getJob());
		System.out.println("가족력: ");
		for (FamilyHistory familyHistory : familyHistoryList.getFamilyHistoryByCID(customer.getCustomerID())) {
			System.out.println("가족관계: " + familyHistory.getRelationship() + "  질환명: " + familyHistory.getDiseaseName());
		}
		System.out.println("\n--------보험 약관 안내--------");
		System.out
				.println("제1조(보험계약의 성립)\n" + "  ① 보험계약은 보험계약자의 청약과 보험회사의 승낙으로 이루어집니다...(생략)\n" + "제2조(약관교부 및 설명의무 등) \n"
						+ "  ① 회사는 계약자가 청약한 경우 계약자에게 약관 및 계약자 보관용 청약서(청약서 부본)를 드리고 약관의 중요한 내용을 설명하여 드립니다...(이하생략)");
		System.out.println("\n위 약관에 동의하십니까? (Y/N)");
		if (inputReader.readLine().trim().equals("Y")) {
			return true;
		} else
			return false;
	}

	private static void updateInsuranceDetail(InsuranceList insuranceListImpl, TermsList termsList,
			GuaranteeList guaranteeList, BufferedReader inputReader) throws Exception {
		while (true) {
			System.out.println("****************** 설계서 관리 화면 *******************");
			System.out.println("================설계 보험 리스트=======================");
			showList(insuranceListImpl.getUnregisteredInsuranceList());
			System.out.println("===================================================");
			System.out.println("수정/삭제할 설계서의 보험 ID를 입력하세요. 없으면 x를 입력하세요");
			String insuranceID = "";
			Insurance insurance;
			System.out.println("보험 ID : ");
			insuranceID = inputReader.readLine().trim();
			if (insuranceID.equals("x"))
				break;
			else if (!insuranceListImpl.isExistInsuranceDesign(insuranceID)) {
				System.out.println("존재하지 않는 보험 설계서입니다.");
				break;
			}
			if (!insuranceID.equals("x")) {
				insurance = insuranceListImpl.retrieveInsuranceDetail(insuranceID);
				System.out.println("보험종류: " + insurance.getType() + "\n보험명: " + insurance.getInsuranceName()
						+ "\n최대보장한도: " + insurance.getMaxCompensation() + "\n보험기간: " + insurance.getPeriodOfInsurance()
						+ "\n납입기간: " + insurance.getPaymentPeriod() + "\n가입나이: " + insurance.getAgeOfTarget()
						+ "\n납입주기: " + insurance.getPaymentCycle() + "\n보장내용(보통약관)");

				ArrayList<Guarantee> guarantees = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID());
				for (int i = 0; i < guarantees.size(); i++) {
					Guarantee guaranteeByIID = guaranteeList.getAllGuranteeByID(insurance.getInsuranceID()).get(i);
					Terms terms = termsList.getTermsByID(guaranteeByIID.getTermsID());
					System.out.println("약관명: " + terms.getTermsName() + "  약관내용: " + terms.getTermsContent());
				}
				System.out
						.println("배당여부: " + insurance.isDistributionStatus() + "\n주의사항: " + insurance.getPrecaution());
				System.out.println("1. 수정, 2. 삭제");
				String choice = inputReader.readLine().trim();
				if (choice.equals("1"))
					updateInsurance(insurance, guaranteeList, termsList, insuranceListImpl, inputReader);
				else if (choice.equals("2"))
					deleteInsurance(insuranceListImpl, insurance.getInsuranceID(), inputReader);
				else
					System.out.println("잘못된 선택지 입니다.");
			} else if (insuranceID.equals("x"))
				break;
			else if (!insuranceID.equals(null))
				System.out.println("아무것도 입력되지 않았습니다.");
		}
	}

	private static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	private static void createInsurance(InsuranceList insuranceListImpl, TermsList termsListImpl,
			BufferedReader inputReader) throws Exception {

		Insurance insurance = new Insurance();
		System.out.println("--------보험 설계 화면---------");
		System.out.print("보험 ID : ");
		insurance.setInsuranceID(inputReader.readLine().trim());
		System.out.print("보험명 : ");
		insurance.setInsuranceName(inputReader.readLine().trim());
		System.out.print("보험 종류 : ");
		insurance.setType(inputReader.readLine().trim());
		while (true) {
			System.out.print("보험가입금액 : ");
			String input = inputReader.readLine().trim();
			if (isInteger(input)) {
				insurance.setMaxCompensation(Integer.valueOf(input));
				break;
			} else
				System.out.println("숫자로 입력해주세요");
		}
		System.out.print("보험 기간 : ");
		insurance.setPeriodOfInsurance(inputReader.readLine().trim());
		System.out.print("납입 주기 : ");
		insurance.setPaymentCycle(inputReader.readLine().trim());
		System.out.print("납입 기간 : ");
		insurance.setPaymentPeriod(inputReader.readLine().trim());
		System.out.print("가입 나이 : ");
		insurance.setAgeOfTarget(inputReader.readLine().trim());
		while (true) {
			System.out.print("기본 보험료 : ");
			String input = inputReader.readLine().trim();
			if (isInteger(input)) {
				insurance.setBasicPremium(Integer.valueOf(input));
				break;
			} else
				System.out.println("숫자로 입력해주세요");
		}
		System.out.print("요율 : ");
		insurance.setRate(inputReader.readLine().trim());
		System.out.print("배당 여부: ");
		insurance.setDistributionStatus(Boolean.parseBoolean(inputReader.readLine().trim()));
		while (true) {
			System.out.print("보장 내용(약관ID) : ");
			String TermsList = inputReader.readLine().trim();
			String[] termsIDListSplit = TermsList.split(",");
			boolean result = true;
			for (int i = 0; i < termsIDListSplit.length; i++) {
				if (termsListImpl.isExistTermsID(termsIDListSplit[i]) == false) {
					result = false;
					break;
				}
			}
			if (result == false)
				System.out.println("약관이 존재하지 않습니다. 다시 입력해주세요");
			else {
				insurance.setTermsIDList(TermsList);
				break;
			}
		}
		System.out.print("주의사항 : ");
		insurance.setPrecaution(inputReader.readLine().trim());
		System.out.print("보험 면책 기간 : ");
		insurance.setInsuranceClausePeriod(inputReader.readLine().trim());
		System.out.print("입력한 내용을 저장하시겠습니까? (Y/N)");

		while (true) {
			System.out.print("선택 : ");
			String save = inputReader.readLine().trim();
			if (save.equals("Y") && insurance.checkAllFillIn() == true) {
				if (insuranceListImpl.createInsurance(insurance))
					System.out.println("저장되었습니다.");
				else
					System.out.println("저장에 실패했습니다.");
				break;
			} else if (save.equals("Y") && insurance.checkAllFillIn() == false) {
				System.out.println("입력하지 않은 항목이 있습니다. 모든 항목을 입력해 주세요");
				break;
			} else if (save.equals("N")) {
				System.out.println("저장을 취소했습니다.");
				break;
			} else
				System.out.println("Y/N중 하나는 입력하셔야합니다.");
		}
	}

	private static void updateInsurance(Insurance insurance, GuaranteeList guaranteeList, TermsList termsListImpl,
			InsuranceList insuranceListImpl, BufferedReader inputReader) throws Exception {
		String choice = "";
		System.out.println("==========================================");
		System.out.println("수정할 정보의 번호를 선택하고 내용을 입력하세요.");
		System.out.println("1. 보험 이름 : " + insurance.getInsuranceName());
		System.out.println("2. 보험 종류 : " + insurance.getType());
		System.out.println("3. 보험가입금액 : " + insurance.getMaxCompensation());
		System.out.println("4. 보험 기간 : " + insurance.getPeriodOfInsurance());
		System.out.println("5. 납입 주기 : " + insurance.getPaymentCycle());
		System.out.println("6. 납입 기간 : " + insurance.getPaymentPeriod());
		System.out.println("7. 가입 나이 : " + insurance.getAgeOfTarget());
		System.out.println("8. 기본 보험료 : " + insurance.getBasicPremium());
		System.out.println("9. 요율 : " + insurance.getRate());
		System.out.println("10. 배당 여부(False/True) : " + insurance.isDistributionStatus());
		System.out.println("11. 보장 내용(약관ID, 콤마로 구분해주세요) : " + insurance.getTermsIDList());
		System.out.println("12. 주의사항 : " + insurance.getPrecaution());
		System.out.println("13. 보험 면책 기간 : " + insurance.getInsuranceClausePeriod());
		System.out.println("==========================================");
		System.out.print("정보 번호 : ");
		choice = inputReader.readLine().trim();
		System.out.print("수정할 내용 :");
		String content = inputReader.readLine().trim();
		System.out.println("==========================================");
		switch (choice) {
		case ("1"):
			insurance.setInsuranceName(content);
			break;
		case ("2"):
			insurance.setType(content);
			break;
		case ("3"):
			if (isInteger(content))
				insurance.setMaxCompensation(Integer.valueOf(content));
			else
				System.out.println("숫자로 입력되지 않아 수정이 저장되지 않았습니다.");
			break;
		case ("4"):
			insurance.setPeriodOfInsurance(content);
			break;
		case ("5"):
			insurance.setPaymentCycle(content);
			break;
		case ("6"):
			insurance.setPaymentPeriod(content);
			break;
		case ("7"):
			insurance.setAgeOfTarget(content);
			break;
		case ("8"):
			if (isInteger(content))
				insurance.setBasicPremium(Integer.valueOf(content));
			else
				System.out.println("숫자로 입력되지 않아 수정이 저장되지 않았습니다.");
			break;
		case ("9"):
			insurance.setRate(content);
			break;
		case ("10"):
			insurance.setDistributionStatus(Boolean.parseBoolean(content));
			break;
		case ("11"):
			String[] termsIDListSplit = content.split(",");
			boolean result = true;
			for (int i = 0; i < termsIDListSplit.length; i++) {
				if (termsListImpl.isExistTermsID(termsIDListSplit[i]) == false) {
					result = false;
					break;
				}
			}
			if (result) {
				insurance.setTermsIDList(content);
				guaranteeList.deleteGuranteeById(insurance.getInsuranceID());
				;
				Guarantee guarantee = new Guarantee();
				for (int i = 0; i < termsIDListSplit.length; i++) {
					guarantee.setInsuranceID(insurance.getInsuranceID());
					guarantee.setTermsID(termsIDListSplit[i]);
					guaranteeList.create(guarantee);
				}
			} else
				System.out.println("약관 ID가 존재하지 않아 수정이 저장되지 않았습니다.");
			break;
		case ("12"):
			insurance.setPrecaution(content);
			break;
		case ("13"):
			insurance.setInsuranceClausePeriod(content);
			break;
		default:
			System.out.println("올바르지 않은 선택지입니다.");
		}

		System.out.println("수정한 내용을 저장하시겠습니까? (Y/N)");
		System.out.println(insurance.toStringDesignInsurance());
		while (true) {
			System.out.print("선택 : ");
			String save = inputReader.readLine().trim();
			if (save.equals("Y")) {
				if (insuranceListImpl.updateinsurance(insurance))
					System.out.println("수정이 저장되었습니다.");
				else
					System.out.println("수정에 실패했습니다.");
				break;
			} else if (save.equals("N")) {
				System.out.println("저장을 취소했습니다.");
				break;
			} else
				System.out.println("Y/N중 하나는 입력하셔야합니다.");
		}
	}

	private static void deleteInsurance(InsuranceList insuranceListImpl, String insuranceID, BufferedReader inputReader)
			throws Exception {
		System.out.println("보험ID : " + insuranceID + "를 삭제하시겠습니까? (Y/N)");
		while (true) {
			String choice = inputReader.readLine().trim();
			if (choice.equals("Y")) {
				if (insuranceListImpl.deleteInsurance(insuranceID))
					System.out.println("삭제되었습니다.");
				else
					System.out.println("삭제에 실패하였습니다.");
				break;
			} else if (choice.equals("N"))
				System.out.println("삭제가 취소되었습니다.");
			else
				System.out.println("Y/N 중 하나를 입력해야합니다.");
		}
	}

	// uc17) 가입된 보험을 조회한다.
	private static void showSubscriptionInsurance(BufferedReader inputReader, ContractList contractListImpl,
			CustomerList customerListImpl, InsuranceList insuranceListImpl, CompensationClaimList compensationClaim,
			PaymentList paymentListImpl) throws ParseException, Exception {

		// 가입된 보험을 조회하다.
		System.out.println("\n[ 보험자 정보 확인 ]");
		System.out.print("[ 이름 ] : ");
		String customerName = inputReader.readLine().trim();
		System.out.print("[ 전화번호 ] : ");
		String customerPH = inputReader.readLine().trim();

		// A1. 입력된 정보의 사용자가 없는 경우
		String customerId = customerListImpl.getCustomerIdFromNameAndPH(customerName, customerPH);
		if (customerId == null) {
			System.out.println("\n[System] 등록되지 않은 사용자입니다.");
			return;
		}

		System.out.println("\n\n[ 내 보험 리스트 ]");
		ArrayList<Contract> customerContract = contractListImpl.retreiveCustomerContract(customerId);
		ArrayList<String> customerInsuranceId = contractListImpl.getInsuranceIdFromCustomerId(customerId);
		ArrayList<String> customerInsuranceInfo = new ArrayList<>();

		int indexBtn = 0;
		// customerContract와 customerInsuranceId의 크기가 같다고 가정, 같지 않을 시 잘못 등록된 정보
		for (int i = 0; i < customerInsuranceId.size(); i++) {
			Contract contract = customerContract.get(i); // 현재 계약 정보
			ArrayList<String> insuranceInfo = insuranceListImpl
					.getInsuranceNameTypeInfoById(customerInsuranceId.get(i)); // 현재 보험 정보

			// 계약 정보와 보험 정보를 문자열로 merge
			String mergedInfo = String.join(" ", insuranceInfo) + " " + contract.getMaxCompensation() + " "
					+ contract.getPremium() + " " + contract.isCancellation() + " " + contract.getDateOfSubscription()
					+ " " + contract.getDateOfMaturity() + " " + contract.isMaturity() + " " + contract.isResurrection()
					+ " " + contract.getInsurancePeriod() + " " + contract.getPaymentCycle() + " " + indexBtn;
			indexBtn++;
			customerInsuranceInfo.add(mergedInfo); // 합쳐진 정보를 리스트에 추가

		}

		showInsuranceInfoTable(); // 테이블 헤더 출력

		for (String info : customerInsuranceInfo) {
			// 각 열의 너비를 조정하여 사이즈 맞추기
			String[] columns = info.split(" ");
			System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
					centerAlign(columns[0], 15), centerAlign(columns[1], 15), centerAlign(columns[2], 15),
					centerAlign(columns[3], 15), centerAlign(columns[4], 10), centerAlign(columns[5], 12),
					centerAlign(columns[6], 12), centerAlign(columns[7], 10), centerAlign(columns[8], 10),
					centerAlign(columns[9], 12), centerAlign(columns[10], 11), centerAlign(columns[11], 10));
		}

		while (true) {
			System.out.print("\n\n• 버튼을 선택해주세요. (뒤로가기 : x) : ");
			boolean found = false;
			String cancelBtn = inputReader.readLine().trim();

			if (cancelBtn.equalsIgnoreCase("x")) {
				// 뒤로 가기 버튼을 누른 경우
				return;
			}

			String selectedInsuranceId = null;
			ArrayList<Payment> selectedPaymentList = new ArrayList<Payment>();
			for (String insuranceInfo : customerInsuranceInfo) {
				String[] columns = insuranceInfo.split(" ");
				if (columns[11].equals(cancelBtn)) {
					found = true;
					boolean isCancelled = Boolean.parseBoolean(columns[4]);
					boolean isMatured = Boolean.parseBoolean(columns[7]);

					selectedInsuranceId = insuranceListImpl.getInsuranceIdbyName(columns[0]); // 선택된 보험Id
					selectedPaymentList = paymentListImpl.retreiveCustomerInsurancePayment(customerId,
							selectedInsuranceId); // 고객이 해당 보험에 대한 납입(true/false) 리스트
					ArrayList<String> premiumList = contractListImpl.retreivePremiumById(customerId,
							selectedInsuranceId); // 해당 고객과 보험에 대한 보험료 리스트
					double totalPremiumPaid = 0; // 해당 보험에 대해서 납입한 보험료
					// 해당 보험에 대해서 납입 여부가 true인 것만 보험료 정산
					for (int i = 0; i < selectedPaymentList.size(); i++) {
						if (selectedPaymentList.get(i).isWhetherPayment()) {
							for (String premium : premiumList) {
								double premiumAmount = Double.parseDouble(premium);
								totalPremiumPaid += premiumAmount;
							}
						}
					}
					if (isCancelled && isMatured) {
						// A6. 해지된 계약의 해지버튼을 누른 경우
						System.out.println("[System] 이미 만기 해지된 보험입니다.");
						return;
					} else if (!isCancelled && isMatured) {
						// A4. 만기된 보험의 해지 버튼을 누른 경우
						insuranceTermination("만기", customerName, customerPH, insuranceInfo, contractListImpl,
								inputReader, compensationClaim, selectedInsuranceId, customerId, totalPremiumPaid,
								columns[0], isMatured);
						return;
					} else if (!isCancelled && !isMatured) {
						// A3. 만기되지 않은 보험의 해지 버튼을 누른 경우
						insuranceTermination("중도", customerName, customerPH, insuranceInfo, contractListImpl,
								inputReader, compensationClaim, selectedInsuranceId, customerId, totalPremiumPaid,
								columns[0], isMatured);
						return;
					} else if (isCancelled && !isMatured) {
						// 이미 중도해지한 보험의 해지 버튼을 누른 경우
						System.out.println("[System] 이미 중도 해지된 보험입니다.");

					}
					return;
				}
			}

			if (!found) {
				System.out.println("[System] 올바르지 않은 버튼입니다. ");
				continue;
			}
		}

	}

	private static void showInsuranceInfoTable() {
		System.out.println(
				"_______________________________________________________________________________________________________________________________________________________________");
		System.out.printf("\n%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
				centerAlign("보험명", 15), centerAlign("보험종류", 13), centerAlign("최대보장한도", 13), centerAlign("보험료", 13),
				centerAlign("해지여부", 10), centerAlign("가입일자", 8), centerAlign("만기일자", 8), centerAlign("만기여부", 10),
				centerAlign("부활여부", 6), centerAlign("보험기간", 6), centerAlign("납입주기", 6), centerAlign("해지버튼", 4));
		System.out.println(
				"_______________________________________________________________________________________________________________________________________________________________\n");
	}

	private static String centerAlign(String text, int width) {
		int totalWidth = width;
		int padding = (totalWidth - text.length()) / 2;
		return String.format("%-" + totalWidth + "s", " ".repeat(padding) + text + " ".repeat(padding));
	}

	// END Of uc17) 가입된 보험을 조회한다.
	// uc18) 보험을 중도 해지한다. , uc19) 만기 보험을 해지하다.
	private static void insuranceTermination(String insuranceStatus, String customerName, String customerPH,
			String info, ContractList contractListImpl, BufferedReader systemInput,
			CompensationClaimList compensationClaim, String selectedInsuranceId, String selectedCustomerId,
			double totalPremiumPaid, String insuranceName, boolean isMatured) throws Exception {
		System.out.printf("\n\n[ %s 해지 화면 ]", insuranceStatus);
		System.out.println("\n________________________________________");
		System.out.printf("\n%-10s %-10s %-10s\n", centerAlign("이름", 10), centerAlign("전화번호", 10),
				centerAlign("해지하기 버튼", 10));
		System.out.println("________________________________________\n");
		System.out.printf("%-10s %-10s %-10s\n", centerAlign("[입력]", 10), centerAlign("[입력]", 10),
				centerAlign("[Y/N]", 13));

		System.out.printf("\n\n[ %s 해지 정보 입력창 ]", insuranceStatus);
		System.out.print("\n[ 이름 ] : ");
		String inputName = systemInput.readLine().trim();
		System.out.print("[ 전화번호 ] : ");
		String inputPH = systemInput.readLine().trim();
		System.out.print("\n해당 보험을 해지하시겠습니까? (Y/N) : ");
		String inputCancel = systemInput.readLine().trim();

		if (inputName.isEmpty() || inputPH.isEmpty())
			System.out.println("\n[System] 입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
		// A2. 잘못된 이름이나 전화번호를 입력한 경우
		if (!inputName.equals(customerName) || !inputPH.equals(customerPH))
			System.out.println("\n[System] 보험자 정보를 다시 확인해주십시오.");
		if (inputName.equals(customerName) && inputPH.equals(customerPH) && inputCancel.equals("Y")) {
			requestTerminationRefund(insuranceStatus, customerName, customerPH, info, contractListImpl, systemInput,
					compensationClaim, selectedInsuranceId, selectedCustomerId, totalPremiumPaid, insuranceName,
					isMatured);
		}

		if (inputName.equals(customerName) && inputPH.equals(customerPH) && inputCancel.equals("N"))
			System.out.println("[System] 해지 요청을 취소합니다.");

	}

	// End Of uc18) 보험을 중도 해지한다. , uc19) 만기 보험을 해지하다.
	// uc20) 해지환급금 지급을 요청한다.
	@SuppressWarnings("unlikely-arg-type")
	private static void requestTerminationRefund(String insuranceStatus, String customerName, String customerPH,
			String info, ContractList contractListImpl, BufferedReader systemInput,
			CompensationClaimList compensationClaim, String selectedInsuranceId, String selectedCustomerId,
			double totalPremiumPaid, String insuranceName, boolean isMatured) throws Exception {

		if (isMatured) {
			double refundAmount = totalPremiumPaid * 0.8; // 예상 환급금액
			System.out.println("\n\n[ 만기 해지환급금 요청 화면 ]");
			System.out.println("+________________________________________________________+");
			System.out.printf("|   %s님의 %s의 총 납입 보험료     |     %-10.2f원    |\n", customerName, insuranceName,
					totalPremiumPaid);
			System.out.println("+________________________________________________________+");
			System.out.printf("|   %s님의 %s의 예상 환급 금액     |     %-10.2f원    |\n", customerName, insuranceName,
					refundAmount);
			System.out.println("+________________________________________________________+");

			showMaturityTerminationTerms(insuranceName);

			String name;
			String phoneNumber;
			String bankName;
			String accountNumber;
			String accountHolder;

			while (true) {
				System.out.println("\n\n[ 만기해지환급금 요청 정보 입력창 ]");
				System.out.print("\n[ 이름 ]: ");
				name = systemInput.readLine().trim();

				System.out.print("[ 전화번호 ]: ");
				phoneNumber = systemInput.readLine().trim();

				System.out.print("[ 은행명 ]: ");
				bankName = systemInput.readLine().trim();

				System.out.print("[ 계좌번호 ]: ");
				accountNumber = systemInput.readLine().trim();

				System.out.print("[ 예금주명 ]: ");
				accountHolder = systemInput.readLine().trim();

				if (name.isEmpty() || phoneNumber.isEmpty() || bankName.isEmpty() || accountNumber.isEmpty()
						|| accountHolder.isEmpty()) {
					System.out.println("[System] 입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
					continue;
				}

				if (!customerName.equals(name) || !customerPH.equals(phoneNumber)) {
					System.out.println("[System] 올바르지 않은 고객 정보입니다. 다시 입력해주세요.");
					continue;
				}

				// 추가적인 입력 형식 검증
				if (!bankName.matches("[A-Za-z가-힣]+")) {
					System.out.println("[System] 은행명은 알파벳과 공백만 입력 가능합니다. 다시 입력해주세요.");
					continue;
				}

				if (!accountNumber.matches("\\d+")) {
					System.out.println("[System] 계좌번호는 숫자로만 입력 가능합니다. 다시 입력해주세요.");
					continue;
				}

				if (!name.matches("[가-힣a-zA-Z]+")) {
					System.out.println("[System] 이름은 한글 또는 영문 대소문자로만 입력 가능합니다. 다시 입력해주세요.");
					continue;
				}
				break;
			}

			System.out.println(" 해지환급금 약관에 동의하십니까?(Y/N) :");
			String agreement = systemInput.readLine().trim();

			if (agreement.equalsIgnoreCase("Y")) {
				contractListImpl.updateCancellation(selectedCustomerId, selectedInsuranceId);
				if (!contractListImpl.updateCancellation(selectedCustomerId, selectedInsuranceId)) {
					System.out.println("[System] 시스템 상 오류로 정상적으로 처리되지 않았습니다. 다시 시도해 주세요.");
				} else {
					contractListImpl.retrieve();
					requestBanking();
				}

			} else if (agreement.equalsIgnoreCase("N")) {
				System.out.println("[System] 약관 동의를 하지 않은 경우 해지환급금 신청이 불가능합니다.");

			} else {
				System.out.println("[System] 올바르지 않은 입력 값입니다. 다시 입력해주세요.");
			}
		} else {
			// 중도 해지
			double refundAmount = (totalPremiumPaid - (totalPremiumPaid * 0.6) + totalPremiumPaid * 0.3);
			System.out.println("\n\n[ 중도해지환급금 요청 화면 ]");
			System.out.println("+________________________________________________________+");
			System.out.printf("|   %s님의 %s의 총 납입 보험료     |     %-10.2f원    |\n", customerName, insuranceName,
					totalPremiumPaid);
			System.out.println("+________________________________________________________+");
			System.out.printf("|   %s님의 %s의 예상 환급 금액     |     %-10.2f원    |\n", customerName, insuranceName,
					refundAmount);
			System.out.println("+________________________________________________________+");

			showTerminationTerms(insuranceName);

			String name;
			String phoneNumber;
			String bankName;
			String accountNumber;
			String accountHolder;

			while (true) {
				System.out.println("\n\n[ 중도해지환급금 요청 정보 입력창 ]");
				System.out.print("\n[ 이름 ]: ");
				name = systemInput.readLine().trim();

				System.out.print("[ 전화번호 ]: ");
				phoneNumber = systemInput.readLine().trim();

				System.out.print("[ 은행명 ]: ");
				bankName = systemInput.readLine().trim();

				System.out.print("[ 계좌번호 ]: ");
				accountNumber = systemInput.readLine().trim();

				System.out.print("[ 예금주명 ]: ");
				accountHolder = systemInput.readLine().trim();

				if (name.isEmpty() || phoneNumber.isEmpty() || bankName.isEmpty() || accountNumber.isEmpty()
						|| accountHolder.isEmpty()) {
					System.out.println("[System] 입력하지 않은 항목이 있습니다. 모든 항목을 입력해주세요.");
					continue;
				}

				if (!customerName.equals(name) || !customerPH.equals(phoneNumber)) {
					System.out.println("[System] 올바르지 않은 고객 정보입니다. 다시 입력해주세요.");
					continue;
				}

				// 추가적인 입력 형식 검증
				if (!bankName.matches("[A-Za-z가-힣]+")) {
					System.out.println("[System] 은행명은 알파벳과 공백만 입력 가능합니다. 다시 입력해주세요.");
					continue;
				}

				if (!accountNumber.matches("\\d+")) {
					System.out.println("[System] 계좌번호는 숫자로만 입력 가능합니다. 다시 입력해주세요.");
					continue;
				}

				if (!name.matches("[가-힣a-zA-Z]+")) {
					System.out.println("[System] 이름은 한글 또는 영문 대소문자로만 입력 가능합니다. 다시 입력해주세요.");
					continue;
				}
				break;
			}

			System.out.print(" 해지환급금 약관에 동의하십니까?(Y/N) :");
			String agreement = systemInput.readLine().trim();

			if (agreement.equalsIgnoreCase("Y")) {
				contractListImpl.updateCancellation(selectedCustomerId, selectedInsuranceId);
				if (!contractListImpl.updateCancellation(selectedCustomerId, selectedInsuranceId)) {
					System.out.println("[System] 시스템 상 오류로 정상적으로 처리되지 않았습니다. 다시 시도해 주세요.");
				} else {

					requestBanking();
				}

			} else if (agreement.equalsIgnoreCase("N")) {
				System.out.println("[System] 약관 동의를 하지 않은 경우 해지환급금 신청이 불가능합니다.");

			} else {
				System.out.println("[System] 올바르지 않은 입력 값입니다. 다시 입력해주세요.");
			}
		}

	}

	// 중도해지환급금 약관 안내 보기
	private static void showTerminationTerms(String insuranceName) {
		System.out.println(
				"\n\n_______________________________________________________________________________________________________________");
		System.out.println("\n[ 중도해지환급금 약관 안내 ]");
		System.out.printf("\n안녕하세요, [에이쁠조..]입니다. 고객님께서 보유하신 [%s]의 중도해지환급금에 대해 안내드리겠습니다.", insuranceName);
		System.out.println();
		System.out.println("1. 중도해지환급금 계산 방식");
		System.out.println("   중도해지환급금은 다음과 같이 계산됩니다:");
		System.out.println();
		System.out.println("   중도해지수수료 = 납입보험료 * 0.6");
		System.out.println("   정산환급금 = 납입보험료 * 0.3");
		System.out.println("   중도해지환급금 = 납입보험료 - 중도해지수수료 + 정산환급금");
		System.out.println();
		System.out.println("   중도해지수수료는 납입보험료의 60%로 적용되며,");
		System.out.println("   정산환급금은 여러 요소를 고려하지만 대략적으로 납입보험료의 30%로 적용됩니다.");
		System.out.println();
		System.out.println("2. 중도해지환급금 신청 정보");
		System.out.println("   중도해지환급금을 신청하시려면 아래의 정보를 입력해주셔야 합니다:");
		System.out.println();
		System.out.println("   - 고객명: 고객님의 성함을 입력해주세요.");
		System.out.println("   - 전화번호: 고객님의 연락 가능한 전화번호를 입력해주세요.");
		System.out.println("   - 은행명: 환급금을 수령하실 은행의 이름을 입력해주세요. (알파벳과 한글만 입력 가능합니다.)");
		System.out.println("   - 계좌번호: 환급금을 입금받으실 계좌의 번호를 입력해주세요. (숫자로만 입력 가능합니다.)");
		System.out.println("   - 예금주명: 계좌에 등록된 예금주의 성함을 입력해주세요.");
		System.out.println();
		System.out.println("   입력하신 정보는 환급금을 안전하게 지급하기 위해 사용되며, 개인정보 보호 정책에 따라 처리됩니다.");
		System.out.println();
		System.out.println("3. 약관 동의");
		System.out.println("   고객님은 중도해지환급금 신청 시 약관에 동의하셔야 합니다.");
		System.out.println();
		System.out.println("   약관에 동의하시면 [Y]를 입력해주세요. 동의하지 않으시면 [N]을 입력해주세요.");
		System.out.println();
		System.out.println("4. 해지 완료 안내");
		System.out.printf("\n   고객님께서 약관에 동의하셨을 경우, [%s]의 중도해지가 완료되었습니다. 환급금은 계좌로 입금될 예정이며, 입금까지 수일이 소요될 수 있습니다.",
				insuranceName);
		System.out.println();
		System.out.println("   추가 문의 사항이 있으시면 고객센터로 연락해주시기 바랍니다.");
		System.out.println();
		System.out.println("감사합니다. 중도해지환급금 관련하여 궁금하신 사항이 있으시면 언제든지 문의해주세요.");
		System.out.println(
				"_______________________________________________________________________________________________________________");

	}

	// 만기해지환급금 약관 안내 보기
	private static void showMaturityTerminationTerms(String insuranceName) {
		System.out.println(
				"\n\n_______________________________________________________________________________________________________________");
		System.out.println("\n[ 만기해지환급금 약관 안내 ]");
		System.out.printf("\n안녕하세요, [에이쁠조..]입니다. 고객님께서 보유하신 [%s]의 만기해지환급금에 대해 안내드리겠습니다.", insuranceName);
		System.out.println();
		System.out.println("1. 단순환급방식 적용");
		System.out.println("   단순환급방식은 가장 일반적으로 사용되는 해지환급 방식 중 하나입니다. 해당 방식은 다음과 같이 계산됩니다:");
		System.out.println();
		System.out.println("   환급금 = 납입한 보험료 * 환급 비율");
		System.out.println();
		System.out.println("   [에이쁠조..]의 환급 비율은 80%로 정해져 있습니다. 즉, 고객님이 납입한 보험료의 80%를 환급받으실 수 있습니다.");
		System.out.println();
		System.out.println("   예를 들어, 만기 시점까지 총 보험료로 20,040원을 납입하셨다면, 환급금은 다음과 같이 계산됩니다:");
		System.out.println();
		System.out.println("   환급금 = 20,040원 * 80% = 16,032원");
		System.out.println();
		System.out.println("   따라서, 고객님께서는 총 20,040원을 납입한 뒤 16,032원의 환급금을 받으실 수 있게 됩니다.");
		System.out.println();
		System.out.println("   *참고: 환급금은 세금 등의 공제 후 실제로 지급되는 금액입니다.");
		System.out.println();
		System.out.println("2. 입력정보 요청");
		System.out.println("   고객님께서 만기해지환급금을 신청하시려면 아래의 정보를 입력해주셔야 합니다:");
		System.out.println();
		System.out.println("   - 고객명: 고객님의 성함을 입력해주세요.");
		System.out.println("   - 전화번호: 고객님의 연락 가능한 전화번호를 입력해주세요.");
		System.out.println("   - 은행명: 환급금을 수령하실 은행의 이름을 입력해주세요. (알파벳과 한글만 입력 가능합니다.)");
		System.out.println("   - 계좌번호: 환급금을 입금받으실 계좌의 번호를 입력해주세요. (숫자로만 입력 가능합니다.)");
		System.out.println("   - 예금주명: 계좌에 등록된 예금주의 성함을 입력해주세요.");
		System.out.println();
		System.out.println("   입력하신 정보는 환급금을 안전하게 지급하기 위해 사용되며, 개인정보 보호 정책에 따라 처리됩니다.");
		System.out.println();
		System.out.println("3. 약관 동의");
		System.out.println("   고객님은 만기해지환급금 신청 시 약관에 동의하셔야 합니다.");
		System.out.println();
		System.out.println("   약관에 동의하시면 [Y]를 입력해주세요. 동의하지 않으시면 [N]을 입력해주세요.");
		System.out.println();
		System.out.println("4. 해지 완료 안내");
		System.out.printf("\n   고객님께서 약관에 동의하셨을 경우, [%s]의 만기해지가 완료되었습니다. 환급금은 계좌로 입금될 예정이며, 입금까지 수일이 소요될 수 있습니다.",
				insuranceName);
		System.out.println();
		System.out.println("   추가 문의 사항이 있으시면 고객센터로 연락해주시기 바랍니다.");
		System.out.println();
		System.out.println("감사합니다. 만기해지환급금 관련하여 궁금하신 사항이 있으시면 언제든지 문의해주세요.");
		System.out.println(
				"_______________________________________________________________________________________________________________");

	}

	// End Of uc20) 해지환급금 지급을 요청한다.
	// uc26) 보험료 미납자를 조회하다.
	private static void showUnpaidCustomer(BufferedReader inputReader, ContractList contractListImpl,
			CustomerList customerListImpl, InsuranceList insuranceList, FamilyHistoryList familyHistoryListImpl,
			PaymentList paymentListImpl, CompensationClaimList compensationClaimList) throws Exception {

		System.out.println("\n[ 보험료 미납자 리스트 ]");
		System.out.println("_____________________________________________________");
		System.out.printf("\n%-10s %-10s %-10s %-10s \n", centerAlign("고객 ID", 10), centerAlign("이름", 9),
				centerAlign("성별", 9), centerAlign("세부정보 보기 버튼", 9));
		System.out.println("_____________________________________________________\n");

		ArrayList<String> unPaidCustomerId = paymentListImpl.retreiveUnpaidCustomerId();
		ArrayList<Customer> customerInfo = new ArrayList<Customer>();
		ArrayList<FamilyHistory> familyInfo = new ArrayList<FamilyHistory>();
		ArrayList<String> unpaidContractStatus = new ArrayList<>();
		ArrayList<String> unpaidInsuranceId = new ArrayList<>();
		ArrayList<String> unpaidInsuranceName = new ArrayList<>();

		int detailBtn = 0;

		for (String customerId : unPaidCustomerId) {
			Customer customer = customerListImpl.getCustomerByID(customerId);
			System.out.printf("%-10s %-10s %-10s %-10s\n", centerAlign(customer.getCustomerID(), 10),
					centerAlign(customer.getCustomerName(), 10), centerAlign(customer.getEGender().toString(), 10),
					centerAlign(Integer.toString(detailBtn), 12));
			detailBtn++;
		}

		System.out.print("\n\n• 버튼 선택 : ");
		String btnChoice = inputReader.readLine().trim();
		String selectedCustomerId = "";
		if (Integer.parseInt(btnChoice) >= 0 && Integer.parseInt(btnChoice) < unPaidCustomerId.size()) {
			selectedCustomerId = unPaidCustomerId.get(Integer.parseInt(btnChoice));
			customerInfo.add(customerListImpl.retrieveCustomer(selectedCustomerId));
			familyInfo.addAll(familyHistoryListImpl.getAllFamilyHistoryFromId(selectedCustomerId));
			ArrayList<Payment> customerPayments = paymentListImpl.retreiveCustomerPayment(selectedCustomerId);
			for (Payment payment : customerPayments) {
				Contract contract = contractListImpl.getContractByInsuranceID(payment.getInsuranceID());
				if (contract != null && !payment.isWhetherPayment()) {
					unpaidContractStatus.add(contract.isMaturity() + " " + contract.isCancellation());
					unpaidInsuranceId.add(contract.getInsuranceID());
				}
			}
			for (String insurance : unpaidInsuranceId)
				unpaidInsuranceName.addAll(insuranceList.getInsuranceNameById(insurance));
		} else {
			System.out.println("[System] 올바르지 않은 버튼 선택입니다. ");
			return;
		}

		System.out.println("\n[ 고객 정보 화면 ]");
		System.out.println("___________________________________________________________________");
		System.out.printf("\n%-60s \n", centerAlign("고객 정보", 60));
		System.out.println("___________________________________________________________________\n");
		System.out.printf("%60s\n", customerInfo.get(0).toString());

		System.out.println("\n\n___________________________________________________________________\n");
		System.out.println("[      가족력 리스트      /     보유 계약 리스트(보험명, 만기 여부, 해지 여부)    ]");
		System.out.println("___________________________________________________________________\n");

		// 출력할 행의 개수는 네 개의 리스트 중 가장 큰 크기로 설정
		int maxIndex = Math.max(Math.max(familyInfo.size(), unpaidInsuranceName.size()), unpaidContractStatus.size());
		// 각각의 리스트를 인덱스별로 가져와서 출력
		for (int i = 0; i < maxIndex; i++) {
			String familyInfoLine = (i < familyInfo.size()) ? familyInfo.get(i).toString() : "";
			String insuranceNameLine = (i < unpaidInsuranceName.size()) ? unpaidInsuranceName.get(i) : "";
			String contractStatusLine = (i < unpaidContractStatus.size()) ? unpaidContractStatus.get(i) : "";
			String combined = insuranceNameLine + " " + contractStatusLine;
			System.out.printf("%20s %20s \n", centerAlign(familyInfoLine, 30), centerAlign(combined, 30));
		}

		System.out.print("\n\n• 대상자 제외 버튼 선택 (Y/N) : ");
		String choice = inputReader.readLine().trim();
		if (choice.equals("Y")) {
			// A1. 대상자에서 제외하고 싶은 경우
//					customerListImpl.deleteUnpaidCustomer(customerListImpl.retrieveCustomer(selectedCustomerId));
			System.out.println("\n[System] 대상자에서 제외되었습니다.");
		} else {
			System.out.println("\n[System] 대상자 제외를 취소합니다.");
		}
	}

	// End Of uc26) 보험료 미납자를 조회하다.
	// uc36) 고객 납입 여부를 등록하다.
	private static void showPaymentManagement(BufferedReader inputReader, CustomerList customerListImpl,
			ContractList contractListImpl, PaymentList paymentListImpl, InsuranceList insuranceListImpl)
			throws Exception {

		ArrayList<Contract> contractList = contractListImpl.retrieve();
		ArrayList<Customer> customerList = customerListImpl.retrieve();
		ArrayList<Insurance> insuranceList = insuranceListImpl.retrieve();
		ArrayList<String> customerId = new ArrayList<String>();
		ArrayList<String> customerInfo = new ArrayList<String>();
		ArrayList<String> insuranceId = new ArrayList<String>();
		ArrayList<String> insuranceName = new ArrayList<String>();
		ArrayList<Integer> premium = new ArrayList<Integer>();

		for (Contract contract : contractList) {
			customerId.add(contract.getCustomerID());
			insuranceId.add(contract.getInsuranceID());
			premium.add(contract.getPremium());
		}

		// customerId와 customerList를 매치하여 customerName 가져오기
		Map<String, String> customerNameMap = new HashMap<>();
		Map<String, String> customerPHMap = new HashMap<>();
		for (Customer customer : customerList) {
			customerNameMap.put(customer.getCustomerID(), customer.getCustomerName());
			customerPHMap.put(customer.getCustomerID(), customer.getPnumber());
		}

		for (String id : customerId) {
			String name = customerNameMap.get(id);
			String PH = customerPHMap.get(id);
			if (name != null && PH != null)
				customerInfo.add(name + " " + PH);
		}

		// insuranceId와 insuranceList를 매치하여 insuranceName 가져오기
		Map<String, String> insuranceNameMap = new HashMap<>();
		for (Insurance insurance : insuranceList) {
			insuranceNameMap.put(insurance.getInsuranceID(), insurance.getInsuranceName());
		}

		for (String id : insuranceId) {
			String name = insuranceNameMap.get(id);
			if (name != null)
				insuranceName.add(name);
		}

		// premium과 insuranceName 인덱스 별로 합치기
		ArrayList<String> premiumInsuranceList = new ArrayList<>();
		int maxIndex = Math.max(premium.size(), insuranceName.size());
		for (int i = 0; i < maxIndex; i++) {
			String premiumStr = (i < premium.size()) ? premium.get(i).toString() : "";
			String insuranceNameStr = (i < insuranceName.size()) ? insuranceName.get(i) : "";
			String combinedLine = insuranceNameStr + " " + premiumStr;
			premiumInsuranceList.add(combinedLine);
		}
		// customerInfo와 premiumInsuranceList 인덱스 별로 합치기
		ArrayList<String> customerPaymentList = new ArrayList<>();
		int maxIndex2 = Math.max(customerInfo.size(), premiumInsuranceList.size());
		for (int i = 0; i < maxIndex2; i++) {
			String customerInfoStr = (i < customerInfo.size()) ? customerInfo.get(i) : "";
			String premiumInsuranceStr = (i < premiumInsuranceList.size()) ? premiumInsuranceList.get(i) : "";
			String combinedLine = customerInfoStr + " " + premiumInsuranceStr;
			customerPaymentList.add(combinedLine);
		}
		if (customerPaymentList.isEmpty()) {
			// A1. 모든 고객의 납입 내역이 존재하지 않을 경우
			System.out.println("[System] 납입 내역이 존재하지 않습니다.");
			return;
		} else {
			System.out.println("\n[ 납입 관리 화면 ]");
			System.out.println("___________________________________________________________________\n");
			System.out.printf("%10s %10s %20s %10s\n", centerAlign("고객명", 10), centerAlign("고객 연락처", 10),
					centerAlign("보험명", 13), centerAlign("납입내역버튼", 8));
			System.out.println("___________________________________________________________________");
			int paymentBtn = 0;
			for (String paymentLine : customerPaymentList) {
				String[] paymentInfo = paymentLine.split(" ");
				String customerInfoStr = (paymentInfo.length > 0) ? paymentInfo[0] : "";
				String insuranceNameStr = (paymentInfo.length > 1) ? paymentInfo[1] : "";
				String premiumStr = (paymentInfo.length > 2) ? paymentInfo[2] : "";
				System.out.printf("%10s %10s %20s %10s\n", centerAlign(customerInfoStr, 10),
						centerAlign(insuranceNameStr, 10), centerAlign(premiumStr, 10), Integer.toString(paymentBtn));
				paymentBtn++;
			}

			System.out.print("\n\n• 납입내역버튼 선택 (버튼 번호 입력 또는 X 입력하여 종료): ");
			String choice = inputReader.readLine().trim();

			while (!choice.equalsIgnoreCase("X")) {
				int selectedBtn = Integer.parseInt(choice);
				if (selectedBtn >= 0 && selectedBtn < customerPaymentList.size()) {
					String selectedPaymentLine = customerPaymentList.get(selectedBtn);
					String[] selectedPaymentInfo = selectedPaymentLine.split(" ");
					String selectedCustomerName = selectedPaymentInfo[0];
					String selectedInsuranceName = selectedPaymentInfo[2];
					String selectedCustomerId = customerId.get(selectedBtn);
					String selectedInsuranceId = insuranceId.get(selectedBtn);
					String paymentStatus = "";
					ArrayList<String> selectedPaymentList = paymentListImpl.retreiveDateStatusById(selectedCustomerId,
							selectedInsuranceId);
					ArrayList<String> selectedPremium = contractListImpl.retreivePremiumById(selectedCustomerId,
							selectedInsuranceId);

					if (selectedPaymentList.isEmpty()) {
						// A2. 해당 고객의 납입 내역이 존재하지 않을 경우
						System.out.println("[System] " + selectedCustomerName + "님 의 납입내역이 존재하지 않습니다.");
						return;
					} else {
						System.out.println("\n[ 납입 내역 ]");
						System.out.println(
								"____________________________________________________________________________________________________\n");
						System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", centerAlign("고객명", 15),
								centerAlign("보험명", 15), centerAlign("보험료", 15), centerAlign("납입일", 15),
								centerAlign("납입여부", 15), centerAlign("납입상태변경버튼", 15));
						System.out.println(
								"____________________________________________________________________________________________________");
						int paymentStatusBtn = 0;
						for (int i = 0; i < Math.min(selectedPaymentList.size(), selectedPremium.size()); i++) {
							String paymentLine = selectedPaymentList.get(i);
							String[] paymentInfo = paymentLine.split(" ");
							String paymentDate = (paymentInfo.length > 0) ? paymentInfo[0] : "";
							paymentStatus = (paymentInfo.length > 1) ? paymentInfo[1] : "";

							System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n",
									centerAlign(selectedCustomerName, 15), centerAlign(selectedInsuranceName, 15),
									centerAlign(selectedPremium.get(i), 15), centerAlign(paymentDate, 20),
									centerAlign(paymentStatus, 12),
									centerAlign(Integer.toString(paymentStatusBtn), 15));
							paymentStatusBtn++;
						}

					}

					System.out.print("\n\n• 납입상태변경버튼 선택 (버튼 번호 입력 또는 q 입력하여 종료): ");
					String paymentUpdateBtn = inputReader.readLine().trim();
					if (paymentUpdateBtn.equals("q")) {
						return;
					} else {
						int selectedPaymentUpdateBtn = Integer.parseInt(paymentUpdateBtn);
						if (selectedPaymentUpdateBtn >= 0 && selectedPaymentUpdateBtn < Math
								.min(selectedPaymentList.size(), selectedPremium.size())) {
							selectedCustomerId = customerId.get(selectedPaymentUpdateBtn);
							selectedInsuranceId = insuranceId.get(selectedPaymentUpdateBtn);

							if (paymentListImpl.updateWhetherPayment(selectedCustomerId, selectedInsuranceId)) {

								System.out.println("[System] " + selectedCustomerName + "님의 " + selectedInsuranceName
										+ " 납입 상태를 변경했습니다.");

							} else {
								System.out.println("[System] 납입 상태 변경 실패");
							}
						}
					}
					return;

				} else {
					// A3. 잘못된 납임내역버튼을 선택한 경우
					System.out.println("[System] 유효하지 않은 버튼 번호입니다.");
					// 다시 선택
					System.out.print("\n\n• 납입내역버튼 선택 (버튼 번호 입력 또는 q 입력하여 종료): ");
					choice = inputReader.readLine().trim();
				}
			}

		}

	}

	// End Of uc36) 고객 납입 여부를 등록하다.
	// uc39) 보험료를 납부한다.
	private static void showInsurancePayment(BufferedReader inputReader, CustomerList customerListImpl,
			ContractList contractListImpl, PaymentList paymentListImpl, InsuranceList insuranceList) throws Exception {
		System.out.println("\n[ 보험자 정보 확인 ]");
		System.out.print("이름 : ");
		String customerName = inputReader.readLine().trim();
		System.out.print("전화번호 : ");
		String customerPH = inputReader.readLine().trim();

		// A1. 입력된 정보의 사용자가 없는 경우
		String customerId = customerListImpl.getCustomerIdFromNameAndPH(customerName, customerPH);
		if (customerId == null) {
			System.out.println("\n[System] 등록되지 않은 사용자입니다.");
			return;
		}

		ArrayList<String> customerInsuranceIds = contractListImpl.getInsuranceIdFromCustomerId(customerId);

		for (int i = 0; i < contractListImpl.getContractsByCID(customerId).size(); i++) {
			if (contractListImpl.getContractsByCID(customerId).get(i).isCancellation()) {
				customerInsuranceIds.remove(i);
			}
		}

		ArrayList<Insurance> customerInsuranceInfo = new ArrayList<Insurance>();
		ArrayList<Payment> customerPaymentInfo = paymentListImpl.retreiveCustomerPayment(customerId);

		for (int i = 0; i < customerInsuranceIds.size(); i++) {
			customerInsuranceInfo.add(insuranceList.getInsurancebyId(customerInsuranceIds.get(i)));
		}

		System.out.println(
				"_____________________________________________________________________________________________________________");
		System.out.println(
				String.format("%10s%10s%10s%10s%10s%10s%10s", "보험명", "보험구분", "납부여부", "보험료", "미납기간", "미납금", "선택 버튼"));
		System.out.println(
				"_____________________________________________________________________________________________________________");

		// 결과 출력을 위한 변수 초기화
		int index = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		int unpaidDays = 0;
		int unpaidAmount = 0;

		for (Insurance insurance : customerInsuranceInfo) {
			String insuranceId = insurance.getInsuranceID();
			boolean isPaid = false;

			// 납부 여부 확인 및 미납 기간, 미납금 계산
			for (Payment payment : customerPaymentInfo) {
				if (payment.getInsuranceID().equals(insuranceId)) {
					LocalDate paymentDate = payment.getDateOfPayment();
					isPaid = payment.isWhetherPayment();
					if (paymentDate != null) { // 입력된 날짜 값이 null이 아닌 경우에만 처리
						Date paymentDateConverted = java.sql.Date.valueOf(paymentDate); // Date로 변환
						// 현재 날짜와의 차이를 계산하여 미납 기간 구함
						int daysDiff = (int) ((today.getTime() - paymentDateConverted.getTime())
								/ (1000 * 60 * 60 * 24));

						// 만약에 납부되지 않았고, 미납 기간이 0이거나 현재 날짜보다 이전의 납부일인 경우에 미납기간 값을 unpaidDays에 저장
						if (!isPaid && (unpaidDays == 0 || paymentDateConverted.before(today))) {
							unpaidDays = daysDiff;

							// 28일을 기준으로 미납금 누적
							if (unpaidDays > 28) {
								int monthsOverdue = unpaidDays / 28;
								unpaidAmount += insurance.getBasicPremium() * monthsOverdue;
							} else {
								unpaidAmount += insurance.getBasicPremium();
							}
						}
						if (isPaid) {
							unpaidAmount = 0;
							unpaidDays = 0;
						}

					}
				}
			}
			// 선택 버튼 및 납부 방법 변경 버튼 표시
			String selectionButton = String.valueOf(20 + index);
			String paymentButton = String.valueOf(10 + index);

			// 결과 출력
			System.out.println(
					String.format("%10s%10s%13s%13s%8s%14s%8s", insurance.getInsuranceName(), insurance.getType(),
							isPaid, insurance.getBasicPremium(), unpaidDays, unpaidAmount, selectionButton));

			index++;
			unpaidAmount = 0; // 초기화

		}

		// 사용자에게 선택 버튼 입력 받기
		System.out.print("\n\n• 선택 버튼 입력 : ");
		String selectionInput = inputReader.readLine().trim();

		if (selectionInput.startsWith("2")) {
			// 보험 선택 후 납입 (완납/일부납)
			insurancePremiumPayment(contractListImpl, inputReader, paymentListImpl, customerInsuranceInfo,
					customerPaymentInfo, selectionInput);
		} else {
			System.out.println("[System] 잘못된 버튼 선택입니다.");
		}

	}

	private static void insurancePremiumPayment(ContractList contractListImpl, BufferedReader inputReader,
			PaymentList paymentListImpl, ArrayList<Insurance> customerInsuranceInfo,
			ArrayList<Payment> customerPaymentInfo, String selectionInput) throws Exception {
		// 버튼 값이 0으로 시작하는 경우 선택한 인덱스에 해당하는 정보 출력
//			String selectedIndexString = ("2" + selectionInput);
		int selectedIndex = Integer.parseInt(selectionInput) - 20;

		Insurance selectedInsurance = customerInsuranceInfo.get(selectedIndex);
		Payment selectedPayment = customerPaymentInfo.get(selectedIndex);

		if (selectedPayment.isWhetherPayment()) {
			System.out.println("[System] 이미 납부된 보험입니다.");
			return;
		} else {
			if (selectedIndex >= 0 && selectedIndex < customerPaymentInfo.size()) {
				// 선택한 인덱스에 해당하는 정보 출력
				System.out.println("\n_______________________________________________________________");
				System.out.println(String.format("%10s%10s%10s%10s", "보험명", "보험구분", "보험료", "납부하기"));
				System.out.println("_______________________________________________________________");

				String insuranceName = selectedInsurance.getInsuranceName();
				String insuranceType = selectedInsurance.getType();
				int basicPremium = selectedInsurance.getBasicPremium();

				System.out.println(
						String.format("%10s%10s%10s%10s", insuranceName, insuranceType, basicPremium, selectionInput));
				System.out.println("\n\n• 납부하기 버튼 입력 :");
				String paymentButtonInput = inputReader.readLine().trim();
				// 입력한 납부하기 버튼과 선택한 버튼이 일치할 경우 납부 방법 출력
				if (paymentButtonInput.equals(selectionInput)) {
					System.out.println("\n_______________________________________________");
					System.out.println(String.format("%25s", "납부 방법 선택"));
					System.out.println("_______________________________________________");
					System.out.println(String.format("%25s", "1. 월보험료(완납)"));
					System.out.println(String.format("%25s", "2. 월보험료(일부납)"));

					System.out.print("\n\n• 납부 방법 버튼 선택 (1/2) : ");
					String paymentMethodInput = inputReader.readLine().trim();

					if (paymentMethodInput.equals("1")) {
						// 월보험료(완납) 버튼을 클릭한 경우

						// 납입 여부, 납부일 업데이트
						for (Payment payment : customerPaymentInfo) {
							if (payment.getInsuranceID().equals(selectedPayment.getInsuranceID())
									&& payment.getCustomerID().equals(selectedPayment.getCustomerID())
									&& payment.getDateOfPayment().equals(selectedPayment.getDateOfPayment())) {
								System.out.println("[System] 월보험료(완납) 납부가 완료되었습니다.");
								payment.setDateOfPayment(LocalDate.now());
								payment.setWhetherPayment(true);
								paymentListImpl.update(payment);
								paymentListImpl.updateWhetherPayment(selectedPayment.getCustomerID(),
										selectedPayment.getInsuranceID());
							}
						}

					} else if (paymentMethodInput.equals("2")) {
						// A3. 월보험료(일부납) 버튼을 클릭할 경우
						System.out.print("\n• 일부 납부할 금액을 입력해 주세요: ");
						String partialPaymentInput = inputReader.readLine().trim();
						int partialPayment = Integer.parseInt(partialPaymentInput);

						if (partialPayment <= 0 || partialPayment > selectedInsurance.getBasicPremium()) {
							System.out.println("[System] 잘못된 금액입니다.");
						} else {
							int remainingAmount = selectedInsurance.getBasicPremium() - partialPayment;
							System.out.println("[System] " + partialPayment + "원 납부가 완료되었습니다. 남은 보험료는 "
									+ remainingAmount + "원입니다.");

							// 보험료 업데이트
							selectedInsurance.setBasicPremium(remainingAmount);

							// 납입 여부 업데이트 (보험료가 0이 된 경우에만 true로 변경)
							if (remainingAmount == 0) {
								for (Payment payment : customerPaymentInfo) {
									if (payment.getInsuranceID().equals(selectedInsurance.getInsuranceID())) {
										payment.setDateOfPayment(LocalDate.now());
										payment.setWhetherPayment(true);
										paymentListImpl.update(payment);
									}
								}
							}

						}
					}
				} else {
					System.out.println("[System] 보험료 납부를 취소했습니다.");
				}
			} else {
				System.out.println("[System] 잘못된 버튼 선택입니다.");
			}
		}

	}

	// End Of ux39) 보험료를 납부한다.

}