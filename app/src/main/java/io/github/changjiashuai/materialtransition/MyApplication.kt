package io.github.changjiashuai.materialtransition

import android.app.Application
import android.os.StrictMode
import com.exyui.android.debugbottle.components.DTInstaller
import com.squareup.leakcanary.LeakCanary

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2016/10/27 15:11.
 */
class MyApplication : Application() {
    override fun onCreate() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog().build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build())
        super.onCreate()
        DTInstaller.install(this)
                .setBlockCanary(AppBlockCanaryContext(this))
//                .setInjector("io.github.changjiashuai.materialtransition.injector.ContentInjector")
                .enable()
                .run()
        LeakCanary.install(this)
    }
}