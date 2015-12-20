#import <Cordova/CDVPlugin.h>
#import "UPPayPlugin.h"

@interface Unionpay : CDVPlugin <UPPayPluginDelegate>
{
    NSString* _mode;
    NSString* _callbackId;
}

- (void)pay:(CDVInvokedUrlCommand*)command;

- (void)UPPayPluginResult:(NSString *)result;

@end
