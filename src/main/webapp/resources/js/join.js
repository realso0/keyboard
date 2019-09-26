// value
var count = 1;
var statList = [];
var resultStat = [];
$(function(){
	// 성별 선택
	$('.genderBox li').click(function(){
		var index = $("li").index(this);
		var selectTag = $("li:eq("+index+")");
		// 초기화
		$('.genderBox li').removeClass("select");
   		selectTag.addClass("select")
   		selectGender = selectTag.text()
   		$(".genderSelect").val(selectGender)
	});

	// 취향 선택
	$(".statBox li").click(function(){
		chkStat($(this));
		$(".statSelect").val(statList);
	});

	
	// 회원가입
	$(".joinTrigger").click(function(){
		// id
		var idRe = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
		var $id = $(".idInputBox");
		var $id_vali = $(".valiChk_id");
		if($id.val() == ""){
			$id_vali.text("아이디를 입력해주세요.");
			$id.focus();
			return false;
		}else if(!idRe.test($id.val())){
			$id_vali.text("이메일 형식의 아이디를 입력해주세요");
			$id.focus();
			return false;
		}else{
			$id_vali.text("");
		}
		
		// password
		var pwRe = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		var $pw = $(".pwChk1");
		var $pw_vali = $(".valiChk_pw");
		if($pw.val() == ""){
			$pw_vali.text("비밀번호를 입력해주세요.");
			$pw.focus();
			return false;
		}else if(pwRe.test($pw.val())){
			$pw_vali.text("비밀번호에 한글 입력은 불가합니다.");
			$pw.focus();
			return false;
		}else{
			$id_vali.text("");
		}
		
		// password check
		var $pw = $(".pwChk1");
		var $pwChk = $(".pwChk2");
		var $pw_vali2 = $(".valiChk_pw2");
		if($pw.val() != $pwChk.val()){
			$pw_vali2.text("비밀번호가 일치하지 않습니다.");
			$pwChk.focus();
			return false;
		}else{
			$pw_vali2.text("");
		}
		
		// password find question
		var $pwq = $(".pwQuestion");
		var $pwq_vali = $(".valiChk_pwq");
		if($pwq.val() == "질문을 선택해주세요."){
			$pwq_vali.text("질문을 선택해주세요.");
			$pwq.focus();
			return false;
		}else{
			$pwq_vali.text("");
		}
		
		// password find answer
		var $pwa = $(".pwFindA");
		var $pwa_vali = $(".valiChk_pwa");
		if($pwa.val() == ""){
			$pwa_vali.text("답을 입력해주세요.");
			$pwa.focus();
			return false;
		}else{
			$pwa_vali.text("");
		}
		
		// birth - year
		var birthRe = /^[0-9]+$/;
		var $birthY = $(".birth_y");
		var $birth_vali = $(".valiChk_birth");
		var nowYear = new Date().getFullYear();
		if(!birthRe.test($birthY.val())){
			$birth_vali.text("숫자만 입력 가능합니다.");
			$birthY.focus();
			return false;
		}else if($birthY.val() >= nowYear || $birthY.val() < 1900){
			$birth_vali.text("올바른 년도를 입력해주세요.");
			$birthY.focus();
			return false;
		}else{
			$birth_vali.text("");
		}
		
		// birth - month
		var $birthM = $(".birth_m");
		if($birthM == "월"){
			$birth_vali.text("월을 선택해주세요.");
			$birthM.focus();
			return false;
		}else{
			$birth_vali.text("");
		}
		
		// birth - day
		var $birthD = $(".birth_d");
		if($birthD == ""){
			$birth_vali.text("일을 입력해주세요");
			$birthD.focus();
			return false;
		}else if($birthD > 31 || $birthD <= 0){
			$birth_vali.text("올바른 값을 입력해주세요.");
			$birthD.focus();
			return false;
		}else{
			$birth_vali.text("");
		}
		
		// user preference
		if(statList.length < 2){
			$(".valiChk_stat").text("취향을 최소한 2개 이상 선택 해주세요.");
		}else{
			$(".valiChk_stat").text("");
		}
		$(".join").trigger("click");
	});
});

// 취향 체크
function chkStat(userStat){
	var chkStat = userStat.text();
	if(userStat.hasClass("statSel")){
		userStat.removeClass("statSel");
		var delIndex = statList.indexOf(chkStat);
		statList.splice(delIndex, 1);
	}else{
		statList.push(chkStat);
		userStat.addClass("statSel");
	}
}