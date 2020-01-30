<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<head>
<title>Amazin</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<header>
		<h1 class="header"><s:text name="header" /></h1>
		<h2 class="centered"><s:text name="welcome" /></h2>
	</header>
	<nav>
		<ul>
			<li><a href="#"><s:text name="start" /></a></li>
			<li><a href="http://miw.uniovi.es"><s:text name="about" /></a></li>
			<li><a href="mailto:dd@email.com"><s:text name="contact" /></a></li>
		</ul>
	</nav>
	<section>

        <s:url var="localeEN" action="doLogin" >
   			<s:param name="request_locale" >en</s:param>
		</s:url>
		<s:url var='localeES' action="doLogin" >
		   <s:param name="request_locale" >es</s:param>
		</s:url>

		<s:a id="english" href="%{localeEN}" ><s:text name="idioma2" /></s:a>
		<s:a id="spanish" href="%{localeES}" ><s:text name="idioma1" /></s:a>

		<article>
			<label class="mytitle"><s:text name="loginTitle" /></label><br />
			<div style="color: red;">
				<s:property value="#request.mymessage" />
				<br />
			</div>
			<s:form action="login"  >
				<s:textfield name="loginInfo.login" key="loginLogin" />
				<s:password name="loginInfo.password" key="loginPassword" />
				<s:textfield name="loginInfo.captcha" key="loginCaptcha" />
				<s:submit key="loginEnviar" />
			</s:form>

		</article>
		<article>
			<s:a action="register-user-form"><s:text name="loginRegister" /></s:a>
		</article>
	</section>
	<footer>
		<strong><s:text name="footer1" /> </strong><br /> <em><s:text name="footer2" /></em><br/>
	</footer>
</body>
