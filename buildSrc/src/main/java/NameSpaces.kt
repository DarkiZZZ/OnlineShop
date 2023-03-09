object NameSpaces {
    ///////
    private const val appNameSpace = Config.appNameSpace

    //core
    private const val coreNameSpace = "$appNameSpace.core"
    const val coreDetailedInfoApiNameSpace = "$coreNameSpace.page_two_api"
    const val coreDetailedInfoApiInternalNameSpace = "$coreNameSpace.page_two_api_internal"
    const val coreLatestApiNameSpace = "$coreNameSpace.latest_api"
    const val coreLatestApiInternalNameSpace = "$coreNameSpace.latest_api_internal"
    const val coreSaleApiNameSpace = "$coreNameSpace.sale_api"
    const val coreSaleApiInternalNameSpace = "$coreNameSpace.sale_api_internal"
    const val coreSearchApiNameSpace = "$coreNameSpace.search_api"
    const val coreSearchApiInternalNameSpace = "$coreNameSpace.search_api_internal"
    const val coreNavigationNameSpace = "$coreNameSpace.navigation"

    //utils
    private const val utilsNameSpace = "$appNameSpace.utils"
    const val utilsNetworkNameSpace = "$utilsNameSpace.network"
    const val utilsLiveDataNameSpace = "$utilsNameSpace.livedata"

    //ui
    const val coreUiNameSpace = "$coreNameSpace.ui"

    //dagger
    const val coreDaggerNameSpace = "$coreNameSpace.dagger"

    //feature
    private const val featureNameSpace = "$appNameSpace.feature"
    const val cartFeatureNameSpace = "$featureNameSpace.cart"
    const val loginFeatureNameSpace = "$featureNameSpace.login"
    const val pageOneFeatureNameSpace = "$featureNameSpace.page_one"
    const val pageTwoFeatureNameSpace = "$featureNameSpace.page_two"
    const val signInFeatureNameSpace = "$featureNameSpace.sign_in"
    const val profileFeatureNameSpace = "$featureNameSpace.profile"

    //storage
    private const val storageNameSpace = "$appNameSpace.storage"
    const val storageUserDatabaseNameSpace = "$storageNameSpace.user_database"
    const val storageUserDatabaseApiNameSpace = "$storageNameSpace.user_database_api"



}