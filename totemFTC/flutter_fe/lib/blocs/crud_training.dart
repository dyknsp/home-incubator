import 'dart:async';
import 'dart:core';

import 'package:flutter_fe/blocs/crud_api.dart';
import 'package:flutter_simple_dependency_injection/injector.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:logging/logging.dart';

import 'crud_user.dart';

part 'crud_training.g.dart';

class CrudTraining {
  static final Logger log = Logger('CrudTraining');

  final CrudApi _backend;

  // List<CrudEntityTicket> _tickets = [];

  CrudTraining(Injector injector): _backend = injector.get<CrudApi>();

/*
  Future<List<CrudEntityTicket>> loadTickets(int userId) async {
    _tickets = (await _backend.get('/api/tickets/byUser/$userId') as List)
        .map((item) => CrudEntityTicket.fromJson(item)).toList();
    return _tickets;
  }
*/

  clear() {
    // _tickets = [];
  }

}

class CrudTicketsBlock {

}

@JsonSerializable(explicitToJson: true)
class CrudEntityTraining {
  int id;
  @JsonKey(fromJson: dateTimeFromJson, toJson: dateTimeToJson)
  DateTime time;
  CrudEntityUser trainer;
  CrudEntityTrainingType trainingType;
  String? comment;

  CrudEntityTraining({required this.id, required this.time, required this.trainer,
    required this.trainingType, this.comment});
  factory CrudEntityTraining.fromJson(Map<String, dynamic> json) => _$CrudEntityTrainingFromJson(json);
  Map<String, dynamic> toJson() => _$CrudEntityTrainingToJson(this);
}

@JsonSerializable()
class CrudEntityTrainingType {
  String trainingType;
  String trainingName;

  CrudEntityTrainingType({required this.trainingType, required this.trainingName});
  factory CrudEntityTrainingType.fromJson(Map<String, dynamic> json) => _$CrudEntityTrainingTypeFromJson(json);
  Map<String, dynamic> toJson() => _$CrudEntityTrainingTypeToJson(this);
}