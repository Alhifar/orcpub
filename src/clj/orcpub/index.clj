(ns orcpub.index
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [orcpub.oauth :as oauth]))

(defn meta-tag [property content]
  (if content
    [:meta
     {:property property
      :content content}]))

(defn index-page [{:keys [url
                          title
                          description
                          image
                          fb-type]}]
  (html5
   {:lang :en}
   [:head
    (meta-tag "og:url" url)
    (meta-tag "og:type" fb-type)
    (meta-tag "og:title" title)
    (meta-tag "og:description" description)
    (meta-tag "og:image" image)
    (meta-tag "google-signin-client_id" "86323071944-te5j96nbke0duomgm24j2on4rs4p7ob9.apps.googleusercontent.com")
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1"}]
    [:title title]
    (include-css "/css/style.css"
                 "/css/compiled/styles.css")
    [:script
     (format
      "   window.fbAsyncInit = function() {
	  FB.init({
	  appId      : '%s',
	  xfbml      : true,
          cookie     : true,
	  version    : 'v2.9'
	  });
	  FB.AppEvents.logPageView();
	  };

	  (function(d, s, id){
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) {return;}
	  js = d.createElement(s); js.id = id;
	  js.src = \"//connect.facebook.net/en_US/sdk.js\";
	  fjs.parentNode.insertBefore(js, fjs);
	  }(document, 'script', 'facebook-jssdk'));"
      (oauth/app-id url))]]
   [:body {:style "margin:0"}
    [:div#app
     [:div {:style "display:flex;justify-content:space-around"}
      [:img {:src "/image/spiral.gif"
             :style "height:200px;width:200px;margin-top:200px"}]]]
    (include-js "/js/compiled/orcpub.js")
    (include-css "/font-awesome-4.7.0/css/font-awesome.min.css")
    (include-css "https://fonts.googleapis.com/css?family=Open+Sans")
    [:script
     "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

	  ga('create', 'UA-69209720-3', 'auto');
	  ga('send', 'pageview');"]]))