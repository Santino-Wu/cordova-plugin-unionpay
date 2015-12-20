#import "Unionpay.h"
#import <Cordova/CDV.h>
#import "UPPayPlugin.h"

@implementation Unionpay

NSString* const UNIONPAY_MODE_KEY       = @"UnionpayMode";
NSString* const RESULT_SUCCESS          = @"success";
NSString* const RESULT_FAIL             = @"fail";
NSString* const RESULT_CANCEL           = @"cancel";
NSString* const RESULT_SUCCESS_MESSAGE  = @"支付成功";
NSString* const RESULT_FAIL_MESSAGE     = @"支付失败";
NSString* const RESULT_CANCEL_MESSAGE   = @"用户取消支付";

- (void)pluginInitialize {
    NSString* mode = [[self.commandDelegate settings] objectForKey:UNIONPAY_MODE_KEY];

    NSLog(@"Get preference \"%@\": \"%@\"", UNIONPAY_MODE_KEY, mode);

    if (mode) {
        _mode = mode;
    } else {
        _mode = @"01";
    }

    NSLog(@"Initialized payment mode as \"%@\".", _mode);
}

- (void)pay:(CDVInvokedUrlCommand*)command
{
    _callbackId = command.callbackId;

    @try {
        NSString* tn = [command.arguments objectAtIndex:0];

        NSLog(@"Start payment with transaction number \"%@\" and mode \"%@\".", tn, _mode);

        [UPPayPlugin startPay:tn mode:_mode viewController:self.viewController delegate:self];
    }
    @catch (NSException *exception) {
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[exception reason]];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:_callbackId];
    }
    @finally {

    }
}

- (void)UPPayPluginResult:(NSString *)result
{
    NSLog(@"Payment result message: \"%@\"", result);

    CDVPluginResult* pluginResult = nil;

    if ([result isEqualToString:RESULT_SUCCESS])
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:RESULT_SUCCESS_MESSAGE];
    }
    else if ([result isEqualToString:RESULT_FAIL])
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:RESULT_FAIL_MESSAGE];
    }
    else if ([result isEqualToString:RESULT_CANCEL])
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:RESULT_CANCEL_MESSAGE];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:_callbackId];
}

@end
