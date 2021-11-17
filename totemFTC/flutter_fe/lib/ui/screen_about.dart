import 'dart:developer' as developer;

import 'package:flutter/material.dart';

import 'drawer.dart';
import 'widgets/scroll_list_selector.dart';
import 'widgets/ui_subscription.dart';
import 'widgets/ui_visit.dart';

class ScreenAbout extends StatelessWidget {

  const ScreenAbout({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Totem FC'),
      ),
      drawer: MyDrawer(),
      body: const Text('About'),
    );
  }
}
