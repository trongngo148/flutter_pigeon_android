package com.example.pigeon_plugin

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

/** PigeonPlugin */
class PigeonPlugin: FlutterPlugin, Messages.ExampleApi, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context: Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    Messages.ExampleApi.setup(flutterPluginBinding.binaryMessenger, this)
      context = flutterPluginBinding.applicationContext
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    Messages.ExampleApi.setup(binding.binaryMessenger, null)
  }



  override fun getPlatformVersion(): Messages.Version {
    val batteryLevel: Int
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    } else {
        val intent =
                ContextWrapper(context.applicationContext)
                        .registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        batteryLevel =
                intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 /
                        intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
    }
    var result = Messages.Version()
    result.string = batteryLevel.toString()
    return result
  }

    /**
     * This `ActivityAware` [io.flutter.embedding.engine.plugins.FlutterPlugin] is now
     * associated with an [android.app.Activity].
     *
     *
     * This method can be invoked in 1 of 2 situations:
     *
     *
     *  * This `ActivityAware` [io.flutter.embedding.engine.plugins.FlutterPlugin] was
     * just added to a [io.flutter.embedding.engine.FlutterEngine] that was already
     * connected to a running [android.app.Activity].
     *  * This `ActivityAware` [io.flutter.embedding.engine.plugins.FlutterPlugin] was
     * already added to a [io.flutter.embedding.engine.FlutterEngine] and that [       ] was just connected to an [       ].
     *
     *
     * The given [ActivityPluginBinding] contains [android.app.Activity]-related
     * references that an `ActivityAware` [ ] may require, such as a reference to the
     * actual [android.app.Activity] in question. The [ActivityPluginBinding] may be
     * referenced until either [.onDetachedFromActivityForConfigChanges] or [ ][.onDetachedFromActivity] is invoked. At the conclusion of either of those methods, the
     * binding is no longer valid. Clear any references to the binding or its resources, and do not
     * invoke any further methods on the binding or its resources.
     */
    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    /**
     * The [android.app.Activity] that was attached and made available in [ ][.onAttachedToActivity] has been detached from this `ActivityAware`'s [io.flutter.embedding.engine.FlutterEngine] for the purpose of
     * processing a configuration change.
     *
     *
     * By the end of this method, the [android.app.Activity] that was made available in
     * [.onAttachedToActivity] is no longer valid. Any references to the
     * associated [android.app.Activity] or [ActivityPluginBinding] should be cleared.
     *
     *
     * This method should be quickly followed by [ ][.onReattachedToActivityForConfigChanges], which signifies that a new
     * [android.app.Activity] has been created with the new configuration options. That method
     * provides a new [ActivityPluginBinding], which references the newly created and associated
     * [android.app.Activity].
     *
     *
     * Any `Lifecycle` listeners that were registered in [ ][.onAttachedToActivity] should be deregistered here to avoid a possible
     * memory leak and other side effects.
     */
    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

    /**
     * This plugin and its [io.flutter.embedding.engine.FlutterEngine] have been re-attached to
     * an [android.app.Activity] after the [android.app.Activity] was recreated to handle
     * configuration changes.
     *
     *
     * `binding` includes a reference to the new instance of the [ ]. `binding` and its references may be cached and used from now until
     * either [.onDetachedFromActivityForConfigChanges] or [.onDetachedFromActivity]
     * is invoked. At the conclusion of either of those methods, the binding is no longer valid. Clear
     * any references to the binding or its resources, and do not invoke any further methods on the
     * binding or its resources.
     */
    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    /**
     * This plugin has been detached from an [android.app.Activity].
     *
     *
     * Detachment can occur for a number of reasons.
     *
     *
     *  * The app is no longer visible and the [android.app.Activity] instance has been
     * destroyed.
     *  * The [io.flutter.embedding.engine.FlutterEngine] that this plugin is connected to
     * has been detached from its [io.flutter.embedding.android.FlutterView].
     *  * This `ActivityAware` plugin has been removed from its [       ].
     *
     *
     * By the end of this method, the [android.app.Activity] that was made available in [ ][.onAttachedToActivity] is no longer valid. Any references to the
     * associated [android.app.Activity] or [ActivityPluginBinding] should be cleared.
     *
     *
     * Any `Lifecycle` listeners that were registered in [ ][.onAttachedToActivity] or [ ][.onReattachedToActivityForConfigChanges] should be deregistered here to
     * avoid a possible memory leak and other side effects.
     */
    override fun onDetachedFromActivity() {
        TODO("Not yet implemented")
    }
}
